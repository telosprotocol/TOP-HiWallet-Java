package com.topnetwork.coin;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.topj.account.Account;
import org.topj.core.Topj;
import org.topj.methods.Model.RequestModel;
import org.topj.methods.response.AccountInfoResponse;
import org.topj.methods.response.CGPResponse;
import org.topj.methods.response.ChainInfoResponse;
import org.topj.methods.response.NodeInfoResponse;
import org.topj.methods.response.PassportResponse;
import org.topj.methods.response.ResponseBase;
import org.topj.methods.response.tx.XTransactionResponse;
import org.topj.procotol.http.HttpService;
import org.topj.tx.PollingTransactionReceiptProcessor;

import com.alibaba.fastjson.JSON;
import com.base.service.base.system.service.ConfigureService;
import com.common.bean.TransactionPageVO;
import com.gitee.magic.core.exception.ApplicationException;
import com.gitee.magic.core.utils.reflect.TypeReference;
import com.gitee.magic.framework.base.rest.HttpRequest;
import com.gitee.magic.framework.base.rest.HttpWrapper;
import com.gitee.magic.framework.base.rest.RequestMethodEnum;
import com.gitee.magic.framework.base.rest.ResponseResolve;
import com.gitee.magic.framework.base.result.PageResponse;
import com.gitee.magic.framework.base.result.PageResponse.PageInfo;
import com.gitee.magic.framework.base.result.QueryResult;
import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.framework.head.vo.PageVO;
import com.topnetwork.coin.top.TransactionSender;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.top.TimeUnit;
import com.topnetwork.wallet.common.enums.top.TopTxStatus;
import com.topnetwork.wallet.param.wallet.top.GetBaseTokenParam;
import com.topnetwork.wallet.param.wallet.top.StakeGasParam;
import com.topnetwork.wallet.param.wallet.top.TopBaseParam;
import com.topnetwork.wallet.param.wallet.top.TransactionListParam;
import com.topnetwork.wallet.param.wallet.top.TransferParam;
import com.topnetwork.wallet.result.wallet.top.GasInfoResult;
import com.topnetwork.wallet.result.wallet.top.GetBalanceResult;
import com.topnetwork.wallet.result.wallet.top.GetTokenResult;
import com.topnetwork.wallet.result.wallet.top.GetTop2gasRatioResult;
import com.topnetwork.wallet.result.wallet.top.GetTxDepositResult;
import com.topnetwork.wallet.result.wallet.top.GetTxListResult;
import com.topnetwork.wallet.result.wallet.top.TransferGasFreeGasResult;
import com.topnetwork.wallet.result.wallet.top.TransferGasResult;
import com.topnetwork.wallet.result.wallet.top.TransferResult;

/**
 * Top
 *
 * @author user
 */
@Service("topCoinService")
@Transactional
public class TopCoinService implements InitializingBean{

    @Autowired
    private ConfigureService configureService;

    @Value("${top.sdk.node.url:http://fullnode.topscan.io:19081}")
    private String nodeUrl;
    @Value("${top.scan.url:https://www.topscan.io}")
    private String scanUrl;
    @Value("${top.scan.accessId}")
    private String accessId;
    @Value("${top.scan.accessKey}")
    private String accessKey;

    private Topj instance;
    private HttpService httpService;
    private final String token = "123";
    private long estimateGas=945l;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.httpService = new HttpService(nodeUrl);
        this.instance = Topj.build(httpService);
    }
    
    public static void main(String[] args) {
    	HttpService http = new HttpService("http://192.168.50.33:19081");
    	Topj topj = Topj.build(http);
    	topj.setTransactionReceiptProcessor(new PollingTransactionReceiptProcessor(5000, 3));
//    	ResponseBase<CGPResponse> cgpResponse = null;
    	ResponseBase<AccountInfoResponse> accountResponse = null;
		Account account = new Account();
		account.setAddress("T800003a5ad431201da19dc987ef7879879790bd77069f");
		try {
//			topj.passport(account);
//			accountResponse = topj.getAccount(account);
//			account = topj.genAccount();
	        topj.passport(account);
			ResponseBase<Map<String, NodeInfoResponse>> t = topj.queryAllNodeInfo(account);
			System.out.println(t.getData());
			ResponseBase<AccountInfoResponse> info = topj.getAccount(account);
			System.out.println(info.getData().getBalance());
			
			
		} catch (IOException e) {
			throw new ApplicationException(e);
		}
//		BigInteger balance = accountResponse.getData().getBalance();
//		System.out.println("txDepositGasExchangeRatio == " + balance);
		
//    	 ResponseBase<AccountInfoResponse> account1;
//         String accountNotFoundErrorCode = "3";
//         Account account = new Account();
//         account.setAddress("T-0-LauzSQ9zu3fBLP5DHg3tEZztMBAxK2cZ3C");
//         String token = "123";
//         try {
//         	try {
//         		topj.passport(account);
//         	} catch (Exception e) {
//         	}
//             account1 = topj.getAccount(account);
//         } catch (IOException e) {
//             throw new ApplicationException(e);
//         }
//         System.out.println(account1.getData().getAvailableGas());
//    	long availiableGas = 500;//account1.getData().getAvailableGas();
//    	long gas = 945l;
//    	if(availiableGas >= gas) {
//    		System.out.println(0);
//    	}else {
//    		ResponseBase<CGPResponse> cgpResponse = null;
//    		account = topj.genAccount();
//    		try {
//    			topj.passport(account);
//    			cgpResponse = topj.getCGP(account);
//    		} catch (IOException e) {
//    			throw new ApplicationException(e);
//    		}
//    		if(ObjectUtils.isEmpty(cgpResponse) || ObjectUtils.isEmpty(cgpResponse.getData())) {
//    			throw new BusinessException(cgpResponse.getErrMsg());
//    		}
//    		if(cgpResponse.getErrNo() != 0){
//    			throw new BusinessException(cgpResponse.getErrMsg());
//    		}
//    		String txDepositGasExchangeRatio = cgpResponse.getData().getTx_deposit_gas_exchange_ratio();
//    		System.out.println("txDepositGasExchangeRatio == " + txDepositGasExchangeRatio);
//    		//utop
//    		if(availiableGas < 0) {
//    			availiableGas = 0;
//    		}
//    		BigDecimal transferFree =BigDecimal.valueOf(gas-availiableGas).multiply(new BigDecimal(txDepositGasExchangeRatio));
//    		System.out.println(transferFree.divide(BigDecimal.TEN.pow(6)));
//    	}
//        if (account1.getErrNo() != null && accountNotFoundErrorCode.equals(account1.getErrNo().toString())) {
//            throw new BusinessException(CodeRes.CODE_23005);
//        }
//        if (account1.getData() == null) {
//            throw new BusinessException(CodeRes.CODE_23007);
//        }
//        System.out.println(new BigDecimal(chainInfo.getData().getTokenPrice()));
	}
    
    public CGPResponse getCGP() {
    	ResponseBase<CGPResponse> cgpResponse = null;
		Account account = instance.genAccount();
		try {
			instance.passport(account);
			cgpResponse = instance.getCGP(account);
		} catch (IOException e) {
			throw new ApplicationException(e);
		}
		if(ObjectUtils.isEmpty(cgpResponse) || ObjectUtils.isEmpty(cgpResponse.getData())) {
			throw new BusinessException(cgpResponse.getErrMsg());
		}
		if(cgpResponse.getErrNo() != 0){
			throw new BusinessException(cgpResponse.getErrMsg());
		}
		return cgpResponse.getData();
    }


    public GetBalanceResult getTopBalance(GetBaseTokenParam param) {
        GetBalanceResult getBalanceResult = new GetBalanceResult();
        ResponseBase<AccountInfoResponse> accountResponse = null;
		Account account = new Account();
		account.setAddress(param.getAddress());
		account.setIdentityToken(param.getToken());
		try {
			instance.passport(account);
			accountResponse = instance.getAccount(account);
		} catch (IOException e) {
			throw new ApplicationException(e);
		}
//        RESTfulTemplate template = new RESTfulTemplate(scanUrl, accessId, accessKey);
//        GetAccountParam getAccountParam = new GetAccountParam();
//        getAccountParam.setAddress(param.getAddress());
//        AccountResult accountResult = template.getForEntityResult("/account/get_account", getAccountParam, AccountResult.class);
		if (accountResponse.getErrNo() != null && accountResponse.equals(accountResponse.getErrNo().toString())) {
			getBalanceResult.setBalance(BigInteger.ZERO);
        }else {     
        	if(accountResponse.getData() == null) {
        		getBalanceResult.setBalance(BigInteger.ZERO);
        	}else {        		
        		getBalanceResult.setBalance(accountResponse.getData().getBalance());
        	}
        }
        return getBalanceResult;
    }


    public GetTokenResult getToken(TopBaseParam param) {
        GetTokenResult getTokenResult = new GetTokenResult();
        try {
            Account account = new Account();
            account.setAddress(param.getAddress());
            ResponseBase<PassportResponse> passport = null;
            try {
                passport = instance.passport(account);
            } catch (Exception e) {
                getTokenResult.setToken(token);
                return getTokenResult;
            }
            if (StringUtils.isEmpty(passport.getData().getIdentityToken())) {
                getTokenResult.setToken(token);
            } else {
                getTokenResult.setToken(passport.getData().getIdentityToken());
            }
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
        return getTokenResult;
    }

    public AccountInfoResponse getAccount(GetBaseTokenParam param) {
        ResponseBase<AccountInfoResponse> account1;
        String accountNotFoundErrorCode = "3";
        Account account = new Account();
        account.setAddress(param.getAddress());
        account.setIdentityToken(param.getToken());

        try {
            if (StringUtils.isEmpty(account.getIdentityToken())) {
            	account.setIdentityToken(token);
                try {
                    instance.passport(account);
                } catch (Exception e) {
                    account.setIdentityToken(token);
                }
            }
            account1 = instance.getAccount(account);
        } catch (IOException e) {
            throw new ApplicationException(e);
        }
        if (account1.getErrNo() != null && accountNotFoundErrorCode.equals(account1.getErrNo().toString())) {
            throw new BusinessException(CodeRes.CODE_23005);
        }
        if (account1.getData() == null) {
            throw new BusinessException(CodeRes.CODE_23007);
        }
        return account1.getData();
    }

    public QueryResult<List<GetTxListResult>> getTransactionList(TransactionListParam param) {

    	Map<String, Object> params = new HashMap<>();
        params.put("accountAddr", param.getAddress());
        params.put("current", param.getPageIndex());
        params.put("size", param.getPageSize());
        HttpWrapper wrapper = new HttpWrapper();
        HttpRequest request = new HttpRequest(scanUrl + "/v1/tx/page", RequestMethodEnum.POST);
        request.setJsonBodyContent(params);
        request.setHeader(HttpWrapper.CONTENTTYPE, HttpWrapper.CONTENTTYPE_JSON);
        String listJson = wrapper.start(request);
        ResponseResolve resolve=new ResponseResolve(listJson);
		PageVO<TransactionPageVO> response=resolve.analysis(PageVO.class,new TypeReference<PageVO<TransactionPageVO>>(TransactionPageVO.class) {});
		resolve.checkResponse(response);
        
        QueryResult<List<GetTxListResult>> result = new QueryResult<>();
        List<GetTxListResult> resultList = new ArrayList<>();
        if (response.getResult() == null || response.getResult() == null) {
            result.setResult(resultList);
            PageResponse.PageInfo pageInfo = new PageResponse.PageInfo();
            pageInfo.setTotalCount(0);
            result.setPageInfo(pageInfo);
            return result;
        }
        for (TransactionPageVO transactionListResult : response.getResult()) {
            GetTxListResult getTxListResult = new GetTxListResult();
            if (StringUtils.isEmpty(transactionListResult.getTxState()) || transactionListResult.getTxState().equals("queue")) {
                getTxListResult.setStatus(TopTxStatus.pendding);
            } else {
                getTxListResult.setStatus(TopTxStatus.valueOf(transactionListResult.getTxState()));
            }

            if (transactionListResult.getTxDeposit() == null) {
                getTxListResult.setTxDeposit(BigDecimal.ZERO);
            } else {
                getTxListResult.setTxDeposit(new BigDecimal(transactionListResult.getTxDeposit()));
            }
            if (transactionListResult.getUsedDeposit() == null) {
                getTxListResult.setUsedDeposit(BigDecimal.ZERO);
            } else {
                getTxListResult.setUsedDeposit(new BigDecimal(transactionListResult.getUsedDeposit()));
            }
            if (transactionListResult.getUsedGas() == null) {
                getTxListResult.setGasUsed(BigDecimal.ZERO);
            } else {
                getTxListResult.setGasUsed(new BigDecimal(transactionListResult.getUsedGas()));
            }
            getTxListResult.setAmount(transactionListResult.getAmount());
            getTxListResult.setFrom(transactionListResult.getSenderAccount());
            getTxListResult.setHash(transactionListResult.getTxHash());
            getTxListResult.setTimestamp(transactionListResult.getSendTimestamp());
            getTxListResult.setTo(transactionListResult.getReceiverAccount());
            getTxListResult.setTxType(transactionListResult.getTxType());
            getTxListResult.setNote(transactionListResult.getNote());
            if (getTxListResult.getNote() == null) {
                getTxListResult.setNote("");
            }
            resultList.add(getTxListResult);
        }
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotalCount(response.getTotalCount());
        result.setPageInfo(pageInfo);
        result.setResult(resultList);
        return result;
    }

    public GasInfoResult getGasInfo(GetBaseTokenParam param) {

        AccountInfoResponse accountInfoResponse = getAccount(param);
        if (accountInfoResponse == null) {
            throw new BusinessException(CodeRes.CODE_23007);
        }
        //未用质押gas
        BigInteger unusedStakeGas = accountInfoResponse.getUnusedStakeGas();
        //未用免费gas
        BigInteger unusedFreeGas = accountInfoResponse.getUnusedFreeGas();
        //总可用gas
        BigInteger availableGas = BigInteger.valueOf(accountInfoResponse.getAvailableGas());
        //总gas
        GasInfoResult gasInfoResult = new GasInfoResult();
        gasInfoResult.setAvailableGas(availableGas);
        gasInfoResult.setTotalFreeGas(accountInfoResponse.getTotalFreeGas());
        gasInfoResult.setTotalStakeGas(accountInfoResponse.getTotalStakeGas());
        gasInfoResult.setUnUsedFreeGas(unusedFreeGas);
        gasInfoResult.setUnUsedStakeGas(unusedStakeGas);
        gasInfoResult.setGasStakedToken(accountInfoResponse.getGasStakedToken());

        if (unusedStakeGas == null || accountInfoResponse.getTotalStakeGas() == null || unusedFreeGas == null || accountInfoResponse.getTotalFreeGas() == null) {
            gasInfoResult.setStakeRecoverySpeed(BigDecimal.ZERO);
            gasInfoResult.setFreeRecoverySpeed(BigDecimal.ZERO);
            return gasInfoResult;
        }

        if (unusedStakeGas.compareTo(accountInfoResponse.getTotalStakeGas()) >= 0) {
            gasInfoResult.setStakeRecoverySpeed(BigDecimal.ZERO);
            if (unusedFreeGas.compareTo(accountInfoResponse.getTotalFreeGas()) >= 0) {
                gasInfoResult.setFreeRecoverySpeed(BigDecimal.ZERO);
                return gasInfoResult;
            } else {
                //免费有回复速度，质押没有
                gasInfoResult.setFreeRecoverySpeed(getSpeed(unusedFreeGas, accountInfoResponse.getTotalFreeGas(), param.getAddress()));
            }
        } else {
            //质押有回复速度，免费没有
            gasInfoResult.setFreeRecoverySpeed(BigDecimal.ZERO);
            gasInfoResult.setStakeRecoverySpeed(getSpeed(unusedStakeGas.add(unusedFreeGas), accountInfoResponse.getTotalStakeGas().add(accountInfoResponse.getTotalFreeGas()), param.getAddress()));
        }

        return gasInfoResult;
    }

    private BigDecimal getSpeed(BigInteger unused, BigInteger total, String address) {
    	Map<String, Object> params = new HashMap<>();
        params.put("address", address);
        params.put("current", 1L);
        params.put("size", 1L);
        params.put("txType", "transfer");
        HttpWrapper wrapper = new HttpWrapper();
        HttpRequest request = new HttpRequest(scanUrl + "/v1/tx/page", RequestMethodEnum.POST);
        request.setJsonBodyContent(params);
        request.setHeader(HttpWrapper.CONTENTTYPE, HttpWrapper.CONTENTTYPE_JSON);
        String listJson = wrapper.start(request);
        ResponseResolve resolve=new ResponseResolve(listJson);
		PageVO<TransactionPageVO> response=resolve.analysis(PageVO.class,new TypeReference<PageVO<TransactionPageVO>>(TransactionPageVO.class) {});
		resolve.checkResponse(response);
		
//        RestfulTemplate template = new RestfulTemplate(scanUrl);
//        TransactionPageAO transactionListQueryParam = new TransactionPageAO();
//        transactionListQueryParam.setCurrent(1L);
//        transactionListQueryParam.setSize(1L);
//        transactionListQueryParam.setTxType("transfer");
//        transactionListQueryParam.setAccountAddr(address);
//        QueryResult<List<TransactionPageVO>> forEntityPage = template.postForEntityPage("/v1/tx/page", transactionListQueryParam, TransactionPageVO.class);
        long hours;
        if (response.getResult() == null || response.getTotalCount() == null || response.getTotalCount() == 0) {
            hours = 24L;
        } else {
        	TransactionPageVO transactionListResult = response.getResult().get(0);
            //最后一笔交易时间
            long timestamp = transactionListResult.getSendTimestamp();
            //当前时间
            long time = System.currentTimeMillis() / 1000;
            hours = 24 - ((time - timestamp) / 3600);
            if (hours <= 0 || hours >= 24) {
                hours = 24L;
            }
        }
        BigDecimal used = new BigDecimal(total).subtract(new BigDecimal(unused));
        return used.divide(new BigDecimal(hours), 0, BigDecimal.ROUND_HALF_UP);
    }

    public GetTop2gasRatioResult getTop2gasRatio() {
        GetTop2gasRatioResult getTop2gasRatioResult = new GetTop2gasRatioResult();
//        String top2GasConfig = String.valueOf(configureService.getValueByCode("top2GasConfig"));
        instance.setTransactionReceiptProcessor(new PollingTransactionReceiptProcessor(5000, 3));
        Account account = instance.genAccount();
        ResponseBase<ChainInfoResponse> chainInfo = null;
        try {
			instance.passport(account);
			chainInfo = instance.getChainInfo(account);
		} catch (IOException e) {
			throw new ApplicationException(e);
		}
        if(ObjectUtils.isEmpty(chainInfo) || ObjectUtils.isEmpty(chainInfo.getData())) {
        	throw new BusinessException(chainInfo.getErrMsg());
        }
        if(chainInfo.getErrNo() != 0){
        	throw new BusinessException(chainInfo.getErrMsg());
        }
        getTop2gasRatioResult.setRatio(new BigDecimal(chainInfo.getData().getTokenPrice()));
        return getTop2gasRatioResult;
    }
    /**
     *  新版本返回
     * @param param
     * @return
     */
    public TransferGasFreeGasResult getGasFee(GetBaseTokenParam param) {
    	TransferGasFreeGasResult transferGasResult = new TransferGasFreeGasResult();
    	ResponseBase<AccountInfoResponse> res = null;//getAccount(param);
		Account account = new Account();
		account.setAddress(param.getAddress());
		BigDecimal transferFree = BigDecimal.ZERO;
		try {
			instance.passport(account);
			res = instance.getAccount(account);
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
		if (res.getErrNo() != null ) {
			if(res.getErrNo().equals(0)) {
				if(res.getData() != null) {					
					transferFree = getTransferFree(estimateGas,res.getData().getAvailableGas());
				}else {
					throw new BusinessException(CodeRes.CODE_23007);
				}
			}else if("3".equals(res.getErrNo().toString())){
				transferFree = BigDecimal.ZERO;
			}else {
				throw new BusinessException(CodeRes.CODE_23007);
			}
		}
		transferGasResult.setGas(transferFree);
        /**
         * 目前硬编码1min
         */
        transferGasResult.setSpeed(new BigDecimal("1"));
        transferGasResult.setSpeedUnit(TimeUnit.minute);
        return transferGasResult;
    }
    
    public TransferGasResult getGasFee() {
    	TransferGasResult transferGasResult = new TransferGasResult();
		transferGasResult.setGas(BigInteger.valueOf(945));
        /**
         * 目前硬编码1min
         */
        transferGasResult.setSpeed(new BigDecimal("1"));
        transferGasResult.setSpeedUnit(TimeUnit.minute);
        return transferGasResult;
    }
    
    
    private BigDecimal getTransferFree(Long gas,Long availiableGas) {
    	if(gas-Math.abs(availiableGas) <0) {
    		return BigDecimal.ZERO;
    	}else {
    		CGPResponse cgpResponse = getCGP();
    		String txDepositGasExchangeRatio = cgpResponse.getTx_deposit_gas_exchange_ratio();
    		//utop
    		BigDecimal transferFree =BigDecimal.valueOf(Math.abs(gas-Math.abs(availiableGas))).multiply(new BigDecimal(txDepositGasExchangeRatio));
    		return transferFree.divide(BigDecimal.TEN.pow(6));
    	}
    }

    public TransferResult transfer(TransferParam param) {
        return sendTransaction(requestModelString2Map(param.getRequestModel()));
    }

    private Map<String, String> requestModelString2Map(String requestModelString) {
        try {
            RequestModel requestModel = JSON.parseObject(requestModelString, RequestModel.class);
            return requestModel.toMap();
        } catch (IOException e) {
            throw new ApplicationException(e);
        }
    }

    private TransferResult sendTransaction(Map<String, String> args) {
        ResponseBase<XTransactionResponse> xTransactionResponseResponseBase =
                TransactionSender._sendTxCommon(httpService, args);
        TransferResult transferResult = new TransferResult();
        if (xTransactionResponseResponseBase.getErrNo() != 0) {
            throw new BusinessException(xTransactionResponseResponseBase.getErrMsg());
        }
        XTransactionResponse data = xTransactionResponseResponseBase.getData();
        if (data != null) {
            transferResult.setSuccess(data.isSuccess());
            transferResult.setHash(data.getOriginalTxInfo().getTxHash());
        } else {
            transferResult.setSuccess(false);
        }
        return transferResult;
    }

    public TransferResult stakeGas(StakeGasParam param) {
        return sendTransaction(requestModelString2Map(param.getRequestModel()));
    }

    public TransferResult unStakeGas(StakeGasParam param) {
        return sendTransaction(requestModelString2Map(param.getRequestModel()));
    }

    public GetTxDepositResult getTxDeposit() {
        GetTxDepositResult getTop2gasRatioResult = new GetTxDepositResult();
        String top2GasConfig = String.valueOf(configureService.getString("topTxDepositConfig"));
        getTop2gasRatioResult.setDeposit(new BigDecimal(top2GasConfig));
        return getTop2gasRatioResult;
    }
}
