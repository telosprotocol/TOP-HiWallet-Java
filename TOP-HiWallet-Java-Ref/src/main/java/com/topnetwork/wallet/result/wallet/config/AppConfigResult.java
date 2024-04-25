package com.topnetwork.wallet.result.wallet.config;

import io.swagger.v3.oas.annotations.media.Schema;

public class AppConfigResult {

    @Schema(title = "dapp配置")
    private DappResult dappConfig;
    @Schema(title = "staking配置", required = true)
    private StakingResult stakingConfig;
    @Schema(title = "发现配置", required = true)
    private DiscoverResult discoverConfig;


    public DappResult getDappConfig() {
        return dappConfig;
    }

    public void setDappConfig(DappResult dappConfig) {
        this.dappConfig = dappConfig;
    }

    public StakingResult getStakingConfig() {
        return stakingConfig;
    }

    public void setStakingConfig(StakingResult stakingConfig) {
        this.stakingConfig = stakingConfig;
    }

    public DiscoverResult getDiscoverConfig() {
        return discoverConfig;
    }

    public void setDiscoverConfig(DiscoverResult discoverConfig) {
        this.discoverConfig = discoverConfig;
    }
}
