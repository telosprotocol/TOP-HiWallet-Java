package com.topnetwork.wallet.result.wallet.config;

import io.swagger.v3.oas.annotations.media.Schema;

public class StakingResult {

    @Schema(title = "staking描述")
    private String desc;
    @Schema(title = "staking按钮文案")
    private String buttonText;
    @Schema(title = "收益文案")
    private String incomeText;
    @Schema(title = "staking 图片 url")
    private String picUrl;
    @Schema(title = "staking url")
    private String stakingUrl;
    @Schema(title = "staking 开关")
    private Boolean stakingSwitch;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Boolean getStakingSwitch() {
        return stakingSwitch;
    }

    public void setStakingSwitch(Boolean stakingSwitch) {
        this.stakingSwitch = stakingSwitch;
    }

    public String getStakingUrl() {
        return stakingUrl;
    }

    public void setStakingUrl(String stakingUrl) {
        this.stakingUrl = stakingUrl;
    }

    public String getIncomeText() {
        return incomeText;
    }

    public void setIncomeText(String incomeText) {
        this.incomeText = incomeText;
    }
}
