package com.topnetwork.coin;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import com.base.core.context.annotation.Cache;
import com.base.service.base.system.service.ConfigureService;
import com.common.bean.ETHTransactionDetailFromTokenView;
import com.gitee.magic.context.RestConverterEditor;
import com.gitee.magic.core.exception.ApplicationException;
import com.gitee.magic.core.json.JsonObject;
import com.gitee.magic.framework.base.constant.Config;
import com.gitee.magic.framework.base.rest.HttpRequest;
import com.gitee.magic.framework.base.rest.HttpWrapper;
import com.gitee.magic.framework.base.rest.RequestMethodEnum;
import com.gitee.magic.framework.base.rest.RestfulTemplate;
import com.gitee.magic.framework.head.exception.BusinessException;
import com.topnetwork.tokenview.param.BalanceParam;
import com.topnetwork.tokenview.param.TokenPriceParam;
import com.topnetwork.tokenview.result.BalanceResult;
import com.topnetwork.tokenview.result.TokenPriceResult;
import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.common.enums.CurrencyUnitEnum;
import com.topnetwork.wallet.entity.CoinSpecies;
import com.topnetwork.wallet.param.wallet.ETHGetCoinPriceParam;
import com.topnetwork.wallet.param.wallet.ETHGetCoinPriceV2Param;
import com.topnetwork.wallet.param.wallet.ETHGetConfigParam;
import com.topnetwork.wallet.param.wallet.ETHQueryBalanceParam;
import com.topnetwork.wallet.param.wallet.ETHQueryErc20BalanceParam;
import com.topnetwork.wallet.param.wallet.ETHSendTransactionParam;
import com.topnetwork.wallet.param.wallet.ETHTokenTransactionListParam;
import com.topnetwork.wallet.param.wallet.ETHTransactionListParam;
import com.topnetwork.wallet.result.ETHConfigResult;
import com.topnetwork.wallet.result.wallet.ETHGetCoinPriceResult;
import com.topnetwork.wallet.result.wallet.ETHQueryBalanceResult;
import com.topnetwork.wallet.result.wallet.ETHQueryNonceResult;
import com.topnetwork.wallet.result.wallet.ETHSendTransactionResult;
import com.topnetwork.wallet.result.wallet.ETHTransactionHTTPResult;
import com.topnetwork.wallet.result.wallet.ETHTransactionResult;
import com.topnetwork.wallet.result.wallet.EthGasResult;
import com.topnetwork.wallet.result.wallet.gas.GasResult;
import com.topnetwork.wallet.service.CoinSpeciesService;
import com.topnetwork.wallet.tokenprice.entity.TokenPrice;
import com.topnetwork.wallet.tokenprice.service.TokenPriceService;

/**
 * 以太坊
 *
 * @author user
 */
@Service("ethCoinService")
public class EthCoinService implements InitializingBean {

    @Value("${eth.web3j.url}")
    private String ethNodeUrl;
    @Value("${eth.api.url}")
    private String ethApiUrl;
    @Value("${eth.api.key}")
    private String ethApiKey;
    @Value("${eth.web3j.key}")
    private String[] web3jKey;
    private  Web3j web3j;
    @Value("${filevm.web3j.url}")
    private String filevmNodeUrl;
    @Autowired
    private ConfigureService configureService;
    @Autowired
    private CoinSpeciesService coinSpeciesService;

    private volatile List<Web3j> web3jList = null;
    private final String DATA_PREFIX = "0x70a08231000000000000000000000000";
    protected static final Logger LOGGER = LoggerFactory.getLogger(EthCoinService.class);

    private String TOKENVIEW_HOST = "https://www.hiwallet.org/tokenview";
    private String TOKENVIEW_HOTS_TEST = "http://127.0.0.1:8081/tokenview";
    private String TOKENVIEW_ACCESSID = "8412f654f8662d033111fc453edc5b63";
    private String TOKENVIEW_SERCTETKEY = "c4jWPrC8mdRC+Nt3ftdwDDi564O3L0+FqMjRRHwHigBwmmoSZB7Pug==";
    private String ETHERSCAN_GAS_URL = "https://api-cn.etherscan.com/api?module=gastracker&action=gasoracle&apikey=";

    @Override
    public void afterPropertiesSet() throws Exception {
        web3jList = new ArrayList<>();
        for (String s : web3jKey) {
            Web3j web3j = Web3j.build(new HttpService(ethNodeUrl + s));
            web3jList.add(web3j);
        }
        web3j = Web3j.build(new HttpService(filevmNodeUrl));
    }

    public ETHQueryBalanceResult queryBalance(ETHQueryBalanceParam queryETHBalanceParam) {
        EthGetBalance ethGetBalance = null;
        try {
            ethGetBalance = getWeb3j(queryETHBalanceParam.getChainType()).ethGetBalance(queryETHBalanceParam.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
        } catch (Exception e) {
            throw new ApplicationException(e);
        }

        BigDecimal ethBalance = Convert.fromWei(ethGetBalance.getBalance().toString(), Convert.Unit.ETHER);
        ETHQueryBalanceResult ethQueryBalanceResult = new ETHQueryBalanceResult();
        ethQueryBalanceResult.setBalance(ethBalance.toString());
        return ethQueryBalanceResult;
    }


    public ETHQueryBalanceResult queryErc20Balance(ETHQueryErc20BalanceParam queryETHBalanceParam) {
        String balance = null;
        try {
            balance = getWeb3j(queryETHBalanceParam.getChainType()).ethCall(org.web3j.protocol.core.methods.request.Transaction.createEthCallTransaction(queryETHBalanceParam.getAddress(), queryETHBalanceParam.getContract(),
                    DATA_PREFIX + queryETHBalanceParam.getAddress().substring(2)), DefaultBlockParameterName.PENDING)
                    .send().getValue();
            balance = balance == null ? "0" : ("0x".equals(balance) ? "0" : new BigInteger(balance.substring(2), 16).toString());
        } catch (IOException e) {
            throw new ApplicationException(e);
        }
        ETHQueryBalanceResult ethQueryBalanceResult = new ETHQueryBalanceResult();
        ethQueryBalanceResult.setBalance(balance);
        return ethQueryBalanceResult;
    }

    public ETHQueryNonceResult getEthNonce(ETHQueryBalanceParam queryETHBalanceParam) {
        EthGetTransactionCount ethGetTransactionCount = null;
        try {
            ethGetTransactionCount = getWeb3j(queryETHBalanceParam.getChainType()).ethGetTransactionCount(queryETHBalanceParam.getAddress(), DefaultBlockParameterName.PENDING).sendAsync().get();
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        ETHQueryNonceResult ethQueryNonceResult = new ETHQueryNonceResult();
        ethQueryNonceResult.setNonce(nonce);
        return ethQueryNonceResult;
    }
    
    public BigInteger getGasPrice() {
    	BigInteger gasPrice = BigInteger.ZERO;
		try {
			EthGasPrice ethGasPrice = getWeb3j(ChainTypeEnum.FIL_EVM).ethGasPrice().send();
			gasPrice = ethGasPrice.getGasPrice();
		} catch (IOException e) {
			 throw new ApplicationException(e);
		}
        return gasPrice;
    }

    public ETHSendTransactionResult sendTransaction(ETHSendTransactionParam ethSendTransactionParam) {
        EthSendTransaction ethSendTransaction = null;
        try {
            ethSendTransaction = getWeb3j(ethSendTransactionParam.getChainType()).ethSendRawTransaction(ethSendTransactionParam.getHexValue()).send();
        } catch (IOException e) {
            throw new ApplicationException(e);
        }
        if (ethSendTransaction.getError() != null && StringUtils.isEmpty(ethSendTransaction.getTransactionHash())) {
            throw new BusinessException(ethSendTransaction.getError().getMessage());
        }
        ETHSendTransactionResult ethSendTransactionResult = new ETHSendTransactionResult();
        ethSendTransactionResult.setHash(ethSendTransaction.getTransactionHash());
        return ethSendTransactionResult;
    }

    public List<ETHTransactionResult> getEthTransactionListByAccount(ETHTransactionListParam ethTransactionListParam) {

        Map<String, String> params = new HashMap<>();
        params.put("address", ethTransactionListParam.getAddress());
        params.put("apikey", ethApiKey);
        params.put("page", String.valueOf(ethTransactionListParam.getPageIndex()));
        params.put("offset", String.valueOf(ethTransactionListParam.getPageSize()));
        HttpWrapper wrapper = new HttpWrapper();
        HttpRequest request = new HttpRequest(ethApiUrl + "module=account&action=txlist&sort=desc", RequestMethodEnum.GET);
        request.setUrlParams(params);
        String listJson = wrapper.start(request);
        ETHTransactionHTTPResult ethTransactionHTTPResult = null;
        try {
            JsonObject jsonObject = new JsonObject(listJson);
            ethTransactionHTTPResult = RestConverterEditor.converterObject(ETHTransactionHTTPResult.class, jsonObject);
        } catch (Exception e) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(listJson);
            }
            throw new ApplicationException(e);
        }
        return ethTransactionHTTPResult.getResult();
    }

    @Cache(key = "getGasList", expire = 5L)
    public EthGasResult getGasList() {
        HttpWrapper wrapper = new HttpWrapper();
        HttpRequest request = new HttpRequest(ETHERSCAN_GAS_URL + ethApiKey, RequestMethodEnum.GET);
        request.getHeaders().put("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        String content = wrapper.start(request);
        JsonObject jsonObject = new JsonObject(content);
        GasResult gasResult = RestConverterEditor.converterObject(GasResult.class, jsonObject);
        //fast
        String fastGasPrice = gasResult.getResult().getFastGasPrice();
        //normal
        String proposeGasPrice = gasResult.getResult().getProposeGasPrice();
        //low
        String safeGasPrice = gasResult.getResult().getSafeGasPrice();
        EthGasResult ethGasResult = new EthGasResult();
        ethGasResult.setFastest((new BigInteger(fastGasPrice).multiply(new BigInteger("10"))).toString());
        ethGasResult.setSafeLow((new BigInteger(safeGasPrice).multiply(new BigInteger("10"))).toString());
        ethGasResult.setAverage((new BigInteger(proposeGasPrice).multiply(new BigInteger("10"))).toString());
        ethGasResult.setFastWait("0.5");
        ethGasResult.setAvgWait("5");
        ethGasResult.setSafeLowWait("30");
        return ethGasResult;
    }

    @Cache(key = "getGasListNew", expire = 5L)
    public EthGasResult getGasListNew() {
        HttpWrapper wrapper = new HttpWrapper();
        HttpRequest request = new HttpRequest(ETHERSCAN_GAS_URL + ethApiKey, RequestMethodEnum.GET);
        request.getHeaders().put("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        String content = wrapper.start(request);
        JsonObject jsonObject = new JsonObject(content);
        GasResult gasResult = RestConverterEditor.converterObject(GasResult.class, jsonObject);
        //fast
        String fastGasPrice = gasResult.getResult().getFastGasPrice();
        //normal
        String proposeGasPrice = gasResult.getResult().getProposeGasPrice();
        //low
        String safeGasPrice = gasResult.getResult().getSafeGasPrice();
        EthGasResult ethGasResult = new EthGasResult();
        ethGasResult.setFastest(new BigDecimal(fastGasPrice).multiply(new BigDecimal("10")).multiply(new BigDecimal("1.1")).setScale(0, RoundingMode.FLOOR).toPlainString());
        ethGasResult.setSafeLow(new BigDecimal(safeGasPrice).multiply(new BigDecimal("10")).multiply(new BigDecimal("1.1")).setScale(0, RoundingMode.FLOOR).toPlainString());
        ethGasResult.setAverage(new BigDecimal(proposeGasPrice).multiply(new BigDecimal("10")).multiply(new BigDecimal("1.1")).setScale(0, RoundingMode.FLOOR).toPlainString());
        ethGasResult.setFastWait("0.5");
        ethGasResult.setAvgWait("5");
        ethGasResult.setSafeLowWait("30");
        return ethGasResult;
    }

    public List<ETHTransactionResult> queryTxList(ETHTokenTransactionListParam ethTokenTransactionListParam) {
        String url = ethApiUrl + "apikey=" + ethApiKey + "&module=account&action=" +
                ethTokenTransactionListParam.getAction() +
                "&sort=desc&address=" + ethTokenTransactionListParam.getAddress() +
                "&page=" + ethTokenTransactionListParam.getPageIndex() + "&offset=" + ethTokenTransactionListParam.getPageSize();
        if (!StringUtils.isEmpty(ethTokenTransactionListParam.getContractAddress())) {
            url = url + "&contractaddress=" + ethTokenTransactionListParam.getContractAddress();
        }
        HttpWrapper wrapper = new HttpWrapper();
        HttpRequest request = new HttpRequest(url, RequestMethodEnum.GET);
        JsonObject jsonObject;
        String content = "";
        try {
            content = wrapper.start(request);
            jsonObject = new JsonObject(content);
        } catch (Exception e) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(content);
            }
            throw new ApplicationException(e);
        }
        ETHTransactionHTTPResult ethTransactionHTTPResult = RestConverterEditor.converterObject(ETHTransactionHTTPResult.class, jsonObject);
        return ethTransactionHTTPResult.getResult();
    }

    @Autowired
    private TokenPriceService tokenPriceService;

    public ETHGetCoinPriceResult getCoinPrice(ETHGetCoinPriceParam ethGetCoinPriceParam) {
        ETHGetCoinPriceResult ethGetCoinPriceResult = new ETHGetCoinPriceResult();
        if (ethGetCoinPriceParam != null && ethGetCoinPriceParam.getTest() != null && ethGetCoinPriceParam.getTest()) {
            ethGetCoinPriceResult.setCurrent_price("0");
            return ethGetCoinPriceResult;
        }
        List<TokenPrice> tokenPrice = tokenPriceService.findByEnglishName(ethGetCoinPriceParam.getIds().replaceAll("-", " "));
        if (tokenPrice == null || tokenPrice.size() <= 0) {
            ethGetCoinPriceResult.setCurrent_price("0");
            return ethGetCoinPriceResult;
        }
        //使用symbol查询币价
        CoinSpecies coinSpecies =  coinSpeciesService.load(tokenPrice.get(0).getCoinId());
        if(coinSpecies == null) {
        	ethGetCoinPriceResult.setCurrent_price("0");
          return ethGetCoinPriceResult;
        }
        ethGetCoinPriceResult.setCurrent_price(getPriceFromTokenView(coinSpecies.getNickname(), CurrencyUnitEnum.valueOf(ethGetCoinPriceParam.getVsCurrency().toUpperCase())));
        return ethGetCoinPriceResult;
    }

    public String getPriceFromTokenView(String ids, CurrencyUnitEnum currencyUnitEnum) {
        String host = Config.isSystemFlag() ? TOKENVIEW_HOST : TOKENVIEW_HOTS_TEST;
        RestfulTemplate template = new RestfulTemplate(host, TOKENVIEW_ACCESSID, TOKENVIEW_SERCTETKEY);
        TokenPriceParam tokenPriceParam = new TokenPriceParam();
        tokenPriceParam.setChainType(ids);
        tokenPriceParam.setCurrencyUnit(com.topnetwork.tokenview.common.enums.CurrencyUnitEnum.valueOf(currencyUnitEnum.name()));
        TokenPriceResult tokenPriceResult = template.getForEntityResult("/v1/utxo/token_price", tokenPriceParam, TokenPriceResult.class);
        return tokenPriceResult.getCurrentPrice();
    }

    /**
     * 获取链上地址余额
     *
     * @param chainType
     * @param address
     * @param contractAddress
     * @return
     */
    public String getUtxoBalance(String chainType, String address, String contractAddress) {
        String host = Config.isSystemFlag() ? TOKENVIEW_HOST : TOKENVIEW_HOTS_TEST;
        RestfulTemplate template = new RestfulTemplate(host, TOKENVIEW_ACCESSID, TOKENVIEW_SERCTETKEY);
        BalanceParam tokenPriceParam = new BalanceParam();
        tokenPriceParam.setChainType(chainType);
        tokenPriceParam.setAddress(address);
        tokenPriceParam.setContractAddress(contractAddress);
        BalanceResult tokenPriceResult = template.getForEntityResult("/v1/utxo/balance", tokenPriceParam, BalanceResult.class);
        return tokenPriceResult.getBalance();
    }

    public ETHGetCoinPriceResult getCoinPrice(ETHGetCoinPriceV2Param ethGetCoinPriceParam) {
        ETHGetCoinPriceResult ethGetCoinPriceResult = new ETHGetCoinPriceResult();
//        List<TokenPrice> tokenPrice = tokenPriceService.findByCoinId(ethGetCoinPriceParam.getCoinId());
//        if (tokenPrice == null || tokenPrice.size() <= 0) {
//            ethGetCoinPriceResult.setCurrent_price("0");
//            return ethGetCoinPriceResult;
//        }
        CoinSpecies coinSpecies =  coinSpeciesService.load(ethGetCoinPriceParam.getCoinId());
        if(coinSpecies == null) {
        ethGetCoinPriceResult.setCurrent_price("0");
          return ethGetCoinPriceResult;
        }
        ethGetCoinPriceResult.setCurrent_price(getPriceFromTokenView(coinSpecies.getNickname(), ethGetCoinPriceParam.getVsCurrency()));
        return ethGetCoinPriceResult;
    }

    public ETHTransactionResult getTransactionDetail(String hash) {
        EthTransaction transcation;
        try {
            transcation = getWeb3j(null).ethGetTransactionByHash(hash).send();
        } catch (IOException e) {
            throw new ApplicationException(e);
        }
        Transaction result = transcation.getResult();
        ETHTransactionResult transactionResult = new ETHTransactionResult();
        if(!ObjectUtils.isEmpty(result)) {
        	BeanUtils.copyProperties(result, transactionResult);
        	transactionResult.setNonce(result.getNonce().toString());
        	if(result.getGas() != null) {
        		transactionResult.setGas(result.getGas().toString());
        	}
        	if(result.getGasPrice() != null) {        	
        		transactionResult.setGasPrice(result.getGasPrice().toString());
        	}
        }else {
        	//从tokenview 发送 https://documenter.getpostman.com/view/5728777/RzZ6HfX2?version=latest#b9ad5b17-244c-4849-be4f-3d3b15a2d283
        	 HttpWrapper wrapper = new HttpWrapper();
             HttpRequest request = new HttpRequest("https://services.tokenview.com/vipapi/pending/eth/"+hash+"?apikey=L3j7r09qcP7lxnp3vcAnvghU", RequestMethodEnum.GET);
             JsonObject jsonObject;
             String content = "";
             try {
                 content = wrapper.start(request);
                 jsonObject = new JsonObject(content);
             } catch (Exception e) {
                 if (LOGGER.isInfoEnabled()) {
                     LOGGER.info(content);
                 }
                 throw new ApplicationException(e);
             }
             if(!ObjectUtils.isEmpty(jsonObject) && jsonObject.has("data")) {            	 
            	 ETHTransactionDetailFromTokenView res = RestConverterEditor.converterObject(ETHTransactionDetailFromTokenView.class, jsonObject.getJsonObject("data"));
            	 if(!ObjectUtils.isEmpty(res) && !ObjectUtils.isEmpty(res.getTxid())) {
            		 BeanUtils.copyProperties(res, transactionResult);
            		 transactionResult.setHash(res.getTxid());
            		 transactionResult.setGas(res.getGasLimit());
            		 transactionResult.setTimeStamp(res.getTime());
            		 return transactionResult;
            	 }
             }
        	return null;
        }
        return transactionResult;
    }


    public Web3j getWeb3j(ChainTypeEnum chainType) {
    	if(chainType != null && chainType.equals(ChainTypeEnum.FIL_EVM)) {
    		return web3j;
    	}
        int i = (int) (Math.random() * web3jList.size());
        return web3jList.get(i);
    }

    public ETHConfigResult getETHConfig(ETHGetConfigParam param) {
        JsonObject jsonObject = (JsonObject) configureService.getValue("ethGagConfig");
        ETHConfigResult ethConfigResult = new ETHConfigResult();
        if(!ObjectUtils.isEmpty(param.getToken()) && !param.getToken()) {
        	 ethConfigResult.setGasLimit(String.valueOf(jsonObject.get("ethgasLimit")));
        	 ethConfigResult.setShowGasUsed(ethConfigResult.getGasLimit());
        }else {
        	ethConfigResult.setGasLimit(String.valueOf(jsonObject.get("gasLimit")));
        	ethConfigResult.setShowGasUsed(String.valueOf(jsonObject.get("showGasUsed")));
        }
        return ethConfigResult;
    }
}
