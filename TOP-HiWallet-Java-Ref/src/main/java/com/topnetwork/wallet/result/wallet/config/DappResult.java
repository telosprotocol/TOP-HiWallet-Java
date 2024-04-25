package com.topnetwork.wallet.result.wallet.config;

import io.swagger.v3.oas.annotations.media.Schema;

public class DappResult {

    @Schema(title = "测试环境 url")
    private String testUrl;
    @Schema(title = "生产环境 url")
    private String proUrl;

    @Schema(title = "eos节点地址 url")
    private String eosNodeUrl;

    public String getTestUrl() {
        return testUrl;
    }

    public void setTestUrl(String testUrl) {
        this.testUrl = testUrl;
    }

    public String getProUrl() {
        return proUrl;
    }

    public void setProUrl(String proUrl) {
        this.proUrl = proUrl;
    }

    public String getEosNodeUrl() {
        return eosNodeUrl;
    }

    public void setEosNodeUrl(String eosNodeUrl) {
        this.eosNodeUrl = eosNodeUrl;
    }
}
