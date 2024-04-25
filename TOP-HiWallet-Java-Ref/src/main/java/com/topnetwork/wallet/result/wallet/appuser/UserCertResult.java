package com.topnetwork.wallet.result.wallet.appuser;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserCertResult {

    @Schema(title = "证书编号")
    private String certNum;
    @Schema(title = "证书创建时间")
    private Long createTime;

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
