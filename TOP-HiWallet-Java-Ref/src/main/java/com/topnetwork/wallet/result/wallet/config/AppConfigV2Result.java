package com.topnetwork.wallet.result.wallet.config;

import io.swagger.v3.oas.annotations.media.Schema;

public class AppConfigV2Result {

    @Schema(title = "dapp配置")
    private DappResult dappConfig;
    @Schema(title = "staking配置", required = true)
    private StakingV2Result stakingConfig;
    @Schema(title = "发现配置", required = true)
    private DiscoverResult discoverConfig;
    @Schema(title = "法币买卖开关配置", required = true)
    private Boolean topCurrencySwitch;


    public DappResult getDappConfig() {
        return dappConfig;
    }

    public void setDappConfig(DappResult dappConfig) {
        this.dappConfig = dappConfig;
    }

    public StakingV2Result getStakingConfig() {
        return stakingConfig;
    }

    public void setStakingConfig(StakingV2Result stakingConfig) {
        this.stakingConfig = stakingConfig;
    }

    public DiscoverResult getDiscoverConfig() {
        return discoverConfig;
    }

    public void setDiscoverConfig(DiscoverResult discoverConfig) {
        this.discoverConfig = discoverConfig;
    }

	public Boolean getTopCurrencySwitch() {
		return topCurrencySwitch;
	}

	public void setTopCurrencySwitch(Boolean topCurrencySwitch) {
		this.topCurrencySwitch = topCurrencySwitch;
	}
    
}
