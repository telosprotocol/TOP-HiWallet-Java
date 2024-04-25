package com.topnetwork.changelly;

import java.io.Serializable;

/**
 * @ClassName CurrencyInfo
 * @Description
 * @Author bran
 * @Date 2020/6/11 15:10
 */
public class CurrencyInfo implements Serializable {
    private static final long serialVersionUID = 43948582629964283L;
    private String name;
    private String ticker;
    private String fullName;
    /**
     * 是否可用
     */
    private boolean enabled;
    private boolean enabledFrom;
    private boolean enabledTo;
    private boolean fixRateEnabled;
    private int payinConfirmations;
    private String extraIdName;
    private String addressUrl;
    private String transactionUrl;
    private String image;
    private long fixedTime;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getTicker() {
        return ticker;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabledFrom(boolean enabledFrom) {
        this.enabledFrom = enabledFrom;
    }

    public boolean getEnabledFrom() {
        return enabledFrom;
    }

    public void setEnabledTo(boolean enabledTo) {
        this.enabledTo = enabledTo;
    }

    public boolean getEnabledTo() {
        return enabledTo;
    }

    public void setFixRateEnabled(boolean fixRateEnabled) {
        this.fixRateEnabled = fixRateEnabled;
    }

    public boolean getFixRateEnabled() {
        return fixRateEnabled;
    }

    public void setPayinConfirmations(int payinConfirmations) {
        this.payinConfirmations = payinConfirmations;
    }

    public int getPayinConfirmations() {
        return payinConfirmations;
    }

    public void setExtraIdName(String extraIdName) {
        this.extraIdName = extraIdName;
    }

    public String getExtraIdName() {
        return extraIdName;
    }

    public void setAddressUrl(String addressUrl) {
        this.addressUrl = addressUrl;
    }

    public String getAddressUrl() {
        return addressUrl;
    }

    public void setTransactionUrl(String transactionUrl) {
        this.transactionUrl = transactionUrl;
    }

    public String getTransactionUrl() {
        return transactionUrl;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setFixedTime(long fixedTime) {
        this.fixedTime = fixedTime;
    }

    public long getFixedTime() {
        return fixedTime;
    }
}
