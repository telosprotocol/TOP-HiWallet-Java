package com.topnetwork.wallet.param.recharge;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

import java.util.List;

/**
 * @ClassName RechargeListParam
 * @Description
 * @Author bran
 * @Date 2020/9/7 17:11
 */
public class RechargeListParam {

    @Schema(title = "手机验证码", required = true)
    @NotNull(CodeRes.CODE_18005)
    @Length(min = 6, max = 6, message = CodeRes.CODE_18005)
    private String phoneCode;
    @Schema(title = "打币账号与数量列表", required = true)
    @NotNull(CodeRes.CODE_23002)
    private List<RechargeParam> rechargeList;

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public List<RechargeParam> getRechargeList() {
        return rechargeList;
    }

    public void setRechargeList(List<RechargeParam> rechargeList) {
        this.rechargeList = rechargeList;
    }
}
