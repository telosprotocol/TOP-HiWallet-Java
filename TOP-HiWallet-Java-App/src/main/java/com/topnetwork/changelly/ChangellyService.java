package com.topnetwork.changelly;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.core.context.annotation.Cache;
import com.topnetwork.wallet.common.CodeRes;

import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.framework.base.rest.HttpRequest;
import com.gitee.magic.framework.base.rest.HttpWrapper;
import com.gitee.magic.framework.base.rest.RequestMethodEnum;
import com.gitee.magic.context.RestConverterEditor;
import com.gitee.magic.core.exception.ApplicationException;
import com.gitee.magic.core.json.JsonObject;

/**
 * @ClassName ChangellyService
 * @Description
 * @Author bran
 * @Date 2020/6/9 16:32
 */
@Service("changellyService")
@Transactional
public class ChangellyService {

    @Value("${changelly.host}")
    private String host;
    @Value("${changelly.secret}")
    private String secret;
    @Value("${changelly.key}")
    private String key;

    /**
     * 获取交易详情
     *
     * @param tranId 第三方交易id
     * @return JSONObject
     */
    public GetTransactionsResult getTransactions(String tranId) {
        JsonObject body = getTransactionsParam(tranId);
        return RestConverterEditor.converterObject(GetTransactionsResult.class, request(body));
    }

    /**
     * 获取交易状态
     *
     * @param tranId 第三方交易id
     * @return JSONObject
     */
    public GetStatusResult getStatus(String tranId) {
        JsonObject body = getStatusParam(tranId);
        return RestConverterEditor.converterObject(GetStatusResult.class, request(body));
    }


    /**
     * 获取第三方支持币对-启用币对
     *
     * @return JSONObject
     */
    @Cache(key = "getCurrencies", expire = 60L * 60L)
    public GetCurrenciesResult getCurrencies() {
        JsonObject body = getCurrenciesParam();
        return RestConverterEditor.converterObject(GetCurrenciesResult.class, request(body));
    }

    /**
     * 获取所有币对，包括未启用币对
     *
     * @return JSONObject
     */
    @Cache(key = "getCurrenciesFull", expire = 60L * 60L)
    public GetCurrenciesFullResult getCurrenciesFull() {
        JsonObject body = getCurrenciesFullParam();
        return RestConverterEditor.converterObject(GetCurrenciesFullResult.class, request(body));
    }


    /**
     * 获取最小可兑换金额
     *
     * @param from 从当前币
     * @param to   兑换到的币
     * @return JSONObject
     */
//    @RedisCache(key = "getMinAmount", value = "#from+'_'+#to", expireMills = 5L * 60L * 1000L)
    public GetAmountResult getMinAmount(String from, String to) {
        JsonObject body = getMinAmountParam(converterCurrency(from), converterCurrency(to));
        return RestConverterEditor.converterObject(GetAmountResult.class, request(body));
    }

    /**
     * 获取预计兑换数量
     *
     * @param from   从当前币
     * @param to     兑换到的币
     * @param amount 金额
     * @return JSONObject
     */
//    @RedisCache(key = "getExchangeAmount", value = "#from+'_'+#to+'_'+#amount.toString()", expireMills = 5L * 60L * 1000L)
    public GetAmountResult getExchangeAmount(String from, String to, BigDecimal amount) {
        JsonObject body = getExchangeAmountParam(converterCurrency(from), converterCurrency(to), amount);
        return RestConverterEditor.converterObject(GetAmountResult.class, request(body));
    }

    /**
     * 创建交易
     *
     * @param from           从当前币
     * @param to             兑换到的币
     * @param amount         兑换数量
     * @param receiveAddress 接收要兑换的币的地址
     * @param refundAddress  交易失败后退币地址（用户发生交易地址）
     * @return JSONObject
     */
    public CreateTransactionResult createTransaction(String from, String to, BigDecimal amount, String receiveAddress, String refundAddress) {
        JsonObject body = getCreateTransactionParam(converterCurrency(from), converterCurrency(to), amount, receiveAddress, refundAddress);
        return RestConverterEditor.converterObject(CreateTransactionResult.class, request(body));
    }

    /**
     * 校验地址是否可用
     *
     * @param currency 币种
     * @param address  地址
     * @return JSONObject
     */
    @Cache(key = "validateAddress", value = "#currency+'_'+#address", expire = 24L * 60L * 60L)
    public ValidateAddressResult validateAddress(String currency, String address) {
        JsonObject body = getValidateAddressParam(converterCurrency(currency), address);
        return RestConverterEditor.converterObject(ValidateAddressResult.class, request(body));
    }

    public String converterCurrency(String currency) {

        if ("USDT".equalsIgnoreCase(currency.trim())) {
            return "usdt20";
        }
        return currency.trim().toLowerCase();
    }


    private JsonObject request(JsonObject body) {
        HttpWrapper wrapper = new HttpWrapper();
        HttpRequest request = new HttpRequest(host, RequestMethodEnum.POST);
        putHeader(request, body.toString());
        request.setBodyContent(body.toString());
        String content = wrapper.start(request);
        JsonObject jsonObject = new JsonObject(content);
        if (jsonObject.has("error")) {
            JsonObject error = jsonObject.getJsonObject("error");
            ChangellyError error1 = RestConverterEditor.converterObject(ChangellyError.class, error);
            throw new BusinessException(CodeRes.CODE_21012 + ":" + error1.getMessage());
        }
        return jsonObject;
    }

    private void putHeader(HttpRequest request, String body) {
        request.getHeaders().put("Content-Type", "application/json");
        request.getHeaders().put("api-key", key);
        request.getHeaders().put("sign", hmacSHA512(body, secret));
    }

    private JsonObject getBaseParam(ChangellyMethod changellyMethod) {
        JsonObject object = new JsonObject();
        object.put("method", changellyMethod.toString());
        object.put("jsonrpc", "2.0");
        object.put("id", UUID.randomUUID().toString());
        return object;
    }

    private JsonObject getTransactionsParam(String txId) {
        JsonObject params = getBaseParam(ChangellyMethod.getTransactions);
        JsonObject extParam = new JsonObject();
        extParam.put("id", txId);
        params.put("params", extParam);
        return params;
    }

    private JsonObject getStatusParam(String tranId) {
        JsonObject params = getBaseParam(ChangellyMethod.getStatus);
        JsonObject extParam = new JsonObject();
        extParam.put("id", tranId);
        params.put("params", extParam);
        return params;
    }

    private JsonObject getMinAmountParam(String from, String to) {
        JsonObject params = getBaseParam(ChangellyMethod.getMinAmount);
        JsonObject extParam = new JsonObject();
        extParam.put("from", from.toLowerCase());
        extParam.put("to", to.toLowerCase());
        params.put("params", extParam);
        return params;
    }

    private JsonObject getExchangeAmountParam(String from, String to, BigDecimal amount) {
        JsonObject params = getBaseParam(ChangellyMethod.getExchangeAmount);
        JsonObject extParam = new JsonObject();
        extParam.put("from", from.toLowerCase());
        extParam.put("to", to.toLowerCase());
        extParam.put("amount", amount.toString());
        params.put("params", extParam);
        return params;
    }

    private JsonObject getCreateTransactionParam(String from, String to, BigDecimal amount, String receiveAddress, String refundAddress) {
        JsonObject params = getBaseParam(ChangellyMethod.createTransaction);
        JsonObject extParam = new JsonObject();
        extParam.put("from", from.toLowerCase());
        extParam.put("to", to.toLowerCase());
        extParam.put("amount", amount.toString());
        extParam.put("address", receiveAddress);
        extParam.put("refundAddress", refundAddress);
        params.put("params", extParam);
        return params;

    }

    private JsonObject getValidateAddressParam(String currency, String address) {
        JsonObject params = getBaseParam(ChangellyMethod.validateAddress);
        JsonObject extParam = new JsonObject();
        extParam.put("currency", currency.toLowerCase());
        extParam.put("address", address);
        params.put("params", extParam);
        return params;
    }

    private JsonObject getCurrenciesParam() {
        return getBaseParam(ChangellyMethod.getCurrencies);
    }

    private JsonObject getCurrenciesFullParam() {
        return getBaseParam(ChangellyMethod.getCurrenciesFull);
    }


    private String hmacSHA512(String data, String key) {
        String result = "";
        byte[] bytesKey = key.getBytes();
        final SecretKeySpec secretKey = new SecretKeySpec(bytesKey, "HmacSHA512");
        try {
            Mac mac = Mac.getInstance("HmacSHA512");
            mac.init(secretKey);
            final byte[] macData = mac.doFinal(data.getBytes());
            byte[] hex = new Hex().encode(macData);
            result = new String(hex, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
        return result;
    }

    public enum ChangellyMethod {
        /**
         * 方法名字
         */
        getCurrencies,
        getMinAmount,
        getExchangeAmount,
        getCurrenciesFull,
        validateAddress,
        createTransaction,
        getStatus,
        getTransactions
    }

}
