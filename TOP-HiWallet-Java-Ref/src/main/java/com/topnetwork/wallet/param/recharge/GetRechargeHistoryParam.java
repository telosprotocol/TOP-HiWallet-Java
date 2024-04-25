package com.topnetwork.wallet.param.recharge;

import com.base.core.head.ao.PageAO;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName GetRechargeHistoryParam
 * @Description
 * @Author bran
 * @Date 2020/9/4 15:15
 */
public class GetRechargeHistoryParam extends PageAO {

    @Schema(title = "新青年号")
    private String certNum;

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }
}
