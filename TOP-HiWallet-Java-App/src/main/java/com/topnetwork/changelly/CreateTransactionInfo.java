package com.topnetwork.changelly;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName CreateTransactionInfo
 * @Description
 * @Author bran
 * @Date 2020/6/11 15:18
 */
public class CreateTransactionInfo {

    /**
     * 交易id
     */
    private String id;
    private String apiExtraFee;
    private String changellyFee;
    private String payinExtraId;
    private String refundAddress;
    /**
     * 第三方期望接收数量
     */
    private String amountExpectedFrom;
    /**
     * 订单状态
     */
    private String status;
    /**
     * 从当前币换币
     */
    private String currencyFrom;
    /**
     * 要换的币
     */
    private String currencyTo;
    private BigDecimal amountTo;
    private String amountExpectedTo;
    /**
     * 要客户转账的地址
     */
    private String payinAddress;
    /**
     * 客户接收币账户（第三方需要转账的账户地址）
     */
    private String payoutAddress;
    /**
     * 创建时间
     */
    private String createdAt;
    private String redirect;
    private boolean kycRequired;
    private String signature;
    private String binaryPayload;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiExtraFee() {
        return apiExtraFee;
    }

    public void setApiExtraFee(String apiExtraFee) {
        this.apiExtraFee = apiExtraFee;
    }

    public String getChangellyFee() {
        return changellyFee;
    }

    public void setChangellyFee(String changellyFee) {
        this.changellyFee = changellyFee;
    }

    public String getPayinExtraId() {
        return payinExtraId;
    }

    public void setPayinExtraId(String payinExtraId) {
        this.payinExtraId = payinExtraId;
    }

    public String getRefundAddress() {
        return refundAddress;
    }

    public void setRefundAddress(String refundAddress) {
        this.refundAddress = refundAddress;
    }

    public String getAmountExpectedFrom() {
        return amountExpectedFrom;
    }

    public void setAmountExpectedFrom(String amountExpectedFrom) {
        this.amountExpectedFrom = amountExpectedFrom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public BigDecimal getAmountTo() {
        return amountTo;
    }

    public void setAmountTo(BigDecimal amountTo) {
        this.amountTo = amountTo;
    }

    public String getAmountExpectedTo() {
        return amountExpectedTo;
    }

    public void setAmountExpectedTo(String amountExpectedTo) {
        this.amountExpectedTo = amountExpectedTo;
    }

    public String getPayinAddress() {
        return payinAddress;
    }

    public void setPayinAddress(String payinAddress) {
        this.payinAddress = payinAddress;
    }

    public String getPayoutAddress() {
        return payoutAddress;
    }

    public void setPayoutAddress(String payoutAddress) {
        this.payoutAddress = payoutAddress;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public boolean isKycRequired() {
        return kycRequired;
    }

    public void setKycRequired(boolean kycRequired) {
        this.kycRequired = kycRequired;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getBinaryPayload() {
        return binaryPayload;
    }

    public void setBinaryPayload(String binaryPayload) {
        this.binaryPayload = binaryPayload;
    }
}
