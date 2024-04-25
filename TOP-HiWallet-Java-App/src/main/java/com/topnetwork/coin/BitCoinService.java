package com.topnetwork.coin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.topnetwork.wallet.result.wallet.BTCFeeResult;
import com.topnetwork.wallet.result.wallet.BTCTransactionHTTPResult;
import com.topnetwork.wallet.result.wallet.BTCTransactionResult;

import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.framework.base.rest.HttpRequest;
import com.gitee.magic.framework.base.rest.HttpWrapper;
import com.gitee.magic.framework.base.rest.RequestMethodEnum;
import com.gitee.magic.context.RestConverterEditor;
import com.gitee.magic.core.json.JsonArray;
import com.gitee.magic.core.json.JsonObject;

/**
 * 比特币
 *
 * @author user
 */
@Service("bitCoinService")
public class BitCoinService {

    @Value("${btc.api.btcFee}")
    private String btcFeeUrl;
    @Value("${btc.api.url}")
    private String apiUrl;

    public BTCFeeResult getBTCFee() {
        HttpWrapper wrapper = new HttpWrapper();
        HttpRequest request = new HttpRequest(btcFeeUrl, RequestMethodEnum.GET);
        request.getHeaders().put("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        String content = wrapper.start(request);
        JsonObject jsonObject = new JsonObject(content);
        return RestConverterEditor.converterObject(BTCFeeResult.class, jsonObject);
    }

    public BTCTransactionResult getTransactionByHash(String hash) {
        String url = apiUrl + "v3/tx/" + hash + "?verbose=3";
        HttpWrapper wrapper = new HttpWrapper();
        HttpRequest request = new HttpRequest(url, RequestMethodEnum.GET);
        request.getHeaders().put("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        String content = wrapper.start(request);
        JsonObject jsonObject = new JsonObject(content);
        return RestConverterEditor.converterObject(BTCTransactionHTTPResult.class, jsonObject).getData();
    }

    public List<BigDecimal> getBestPrice(Long startTime, Long endTime) {
        List<BigDecimal> list = new ArrayList<>();
        try {
            String url = "https://api.binance.com/api/v3/klines?symbol=BTCUSDT&interval=2h&startTime=" + startTime + "&endTime=" + endTime;
            HttpWrapper wrapper = new HttpWrapper();
            HttpRequest request = new HttpRequest(url, RequestMethodEnum.GET);
            request.getHeaders().put("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            String content = wrapper.start(request);
            JsonArray jsonObject = new JsonArray(content);
            String startPrice = ((JsonArray) jsonObject.get(0)).get(1).toString();
            String endPrice  = ((JsonArray) jsonObject.get(1)).get(1).toString();
            list.add(new BigDecimal(startPrice));
            list.add(new BigDecimal(endPrice));
        } catch (Exception e) {
            throw new BusinessException("getBestPrice error");
        }
        return list;
    }

}
