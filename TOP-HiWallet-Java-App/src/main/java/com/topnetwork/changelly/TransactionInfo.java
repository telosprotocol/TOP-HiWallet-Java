package com.topnetwork.changelly;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName TransactionInfo
 * @Description
 * @Author bran
 * @Date 2020/6/11 15:22
 */
public class TransactionInfo implements Serializable {
    private static final long serialVersionUID = 43948582629964283L;
    /**
     * 交易id
     */
    private String id;
    /**
     * 交易查询url
     */
    private String trackUrl;
    /**
     * 交易创建时间
     */
    private Long createdAt;
    /**
     * 交易类型（浮动、固定费率）
     */
    private String type;
    /**
     * 第三方收到客户转账市价
     */
    private Long moneyReceived;
    /**
     * 第三方发送交易时间
     */
    private Long moneySent;
    private String rate;
    private String payinConfirmations;
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
    /**
     * 要客户转账的地址
     */
    private String payinAddress;
    private String payinExtraId;
    private String payinExtraIdName;
    /**
     * 客户转账hash
     */
    private String payinHash;
    /**
     * 第三方转账查询地址链接
     */
    private String payoutHashLink;
    /**
     * 第三方退款交易查询地址链接
     */
    private String refundHashLink;
    /**
     * 第三方期望接收数量
     */
    private String amountExpectedFrom;
    /**
     * 客户接收币账户（第三方需要转账的账户地址）
     */
    private String payoutAddress;
    private String payoutExtraId;
    private String payoutExtraIdName;
    /**
     * 第三方转账hash
     */
    private String payoutHash;
    /**
     * 第三方退款hash
     */
    private String refundHash;
    /**
     * 第三方接收数量
     */
    private String amountFrom;
    /**
     * 客户获得接收数量
     */
    private String amountTo;
    private String amountExpectedTo;
    private String networkFee;
    private String changellyFee;
    private String apiExtraFee;
    /**
     * 所有交易费
     */
    private String totalFee;
    private String fiatProviderId;
    private String fiatProvider;
    private String fiatProviderRedirect;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrackUrl() {
        return trackUrl;
    }

    public void setTrackUrl(String trackUrl) {
        this.trackUrl = trackUrl;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getMoneyReceived() {
        return moneyReceived;
    }

    public void setMoneyReceived(Long moneyReceived) {
        this.moneyReceived = moneyReceived;
    }

    public Long getMoneySent() {
        return moneySent;
    }

    public void setMoneySent(Long moneySent) {
        this.moneySent = moneySent;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getPayinConfirmations() {
        return payinConfirmations;
    }

    public void setPayinConfirmations(String payinConfirmations) {
        this.payinConfirmations = payinConfirmations;
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

    public String getPayinAddress() {
        return payinAddress;
    }

    public void setPayinAddress(String payinAddress) {
        this.payinAddress = payinAddress;
    }

    public String getPayinExtraId() {
        return payinExtraId;
    }

    public void setPayinExtraId(String payinExtraId) {
        this.payinExtraId = payinExtraId;
    }

    public String getPayinExtraIdName() {
        return payinExtraIdName;
    }

    public void setPayinExtraIdName(String payinExtraIdName) {
        this.payinExtraIdName = payinExtraIdName;
    }

    public String getPayinHash() {
        return payinHash;
    }

    public void setPayinHash(String payinHash) {
        this.payinHash = payinHash;
    }

    public String getPayoutHashLink() {
        return payoutHashLink;
    }

    public void setPayoutHashLink(String payoutHashLink) {
        this.payoutHashLink = payoutHashLink;
    }

    public String getRefundHashLink() {
        return refundHashLink;
    }

    public void setRefundHashLink(String refundHashLink) {
        this.refundHashLink = refundHashLink;
    }

    public String getAmountExpectedFrom() {
        return amountExpectedFrom;
    }

    public void setAmountExpectedFrom(String amountExpectedFrom) {
        this.amountExpectedFrom = amountExpectedFrom;
    }

    public String getPayoutAddress() {
        return payoutAddress;
    }

    public void setPayoutAddress(String payoutAddress) {
        this.payoutAddress = payoutAddress;
    }

    public String getPayoutExtraId() {
        return payoutExtraId;
    }

    public void setPayoutExtraId(String payoutExtraId) {
        this.payoutExtraId = payoutExtraId;
    }

    public String getPayoutExtraIdName() {
        return payoutExtraIdName;
    }

    public void setPayoutExtraIdName(String payoutExtraIdName) {
        this.payoutExtraIdName = payoutExtraIdName;
    }

    public String getPayoutHash() {
        return payoutHash;
    }

    public void setPayoutHash(String payoutHash) {
        this.payoutHash = payoutHash;
    }

    public String getRefundHash() {
        return refundHash;
    }

    public void setRefundHash(String refundHash) {
        this.refundHash = refundHash;
    }

    public String getAmountFrom() {
        return amountFrom;
    }

    public void setAmountFrom(String amountFrom) {
        this.amountFrom = amountFrom;
    }

    public String getAmountTo() {
        return amountTo;
    }

    public void setAmountTo(String amountTo) {
        this.amountTo = amountTo;
    }

    public String getAmountExpectedTo() {
        return amountExpectedTo;
    }

    public void setAmountExpectedTo(String amountExpectedTo) {
        this.amountExpectedTo = amountExpectedTo;
    }

    public String getNetworkFee() {
        return networkFee;
    }

    public void setNetworkFee(String networkFee) {
        this.networkFee = networkFee;
    }

    public String getChangellyFee() {
        return changellyFee;
    }

    public void setChangellyFee(String changellyFee) {
        this.changellyFee = changellyFee;
    }

    public String getApiExtraFee() {
        return apiExtraFee;
    }

    public void setApiExtraFee(String apiExtraFee) {
        this.apiExtraFee = apiExtraFee;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getFiatProviderId() {
        return fiatProviderId;
    }

    public void setFiatProviderId(String fiatProviderId) {
        this.fiatProviderId = fiatProviderId;
    }

    public String getFiatProvider() {
        return fiatProvider;
    }

    public void setFiatProvider(String fiatProvider) {
        this.fiatProvider = fiatProvider;
    }

    public String getFiatProviderRedirect() {
        return fiatProviderRedirect;
    }

    public void setFiatProviderRedirect(String fiatProviderRedirect) {
        this.fiatProviderRedirect = fiatProviderRedirect;
    }
}
