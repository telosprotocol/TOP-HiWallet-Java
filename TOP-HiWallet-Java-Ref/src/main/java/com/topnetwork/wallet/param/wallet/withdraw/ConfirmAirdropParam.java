package com.topnetwork.wallet.param.wallet.withdraw;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;
import com.gitee.magic.core.valid.annotation.Regex;

import java.math.BigDecimal;

/**
 * @ClassName ConfirmAirdropParam
 * @Description
 * @Author bran
 * @Date 2020/7/7 14:45
 */
public class ConfirmAirdropParam {

    @Schema(title = "提现数量", required = true)
    @Format(type = Format.FormatType.DOUBLE, message = CodeRes.CODE_21015)
    @NotNull(CodeRes.CODE_21015)
    @Length(message = CodeRes.CODE_21015)
    private BigDecimal amount;
    @Schema(title = "提现到账地址", required = true)
    @NotNull(CodeRes.CODE_21016)
    @Length(message = CodeRes.CODE_21016)
    @Regex(regex = "^0x[a-fA-F0-9]{40}$", message = CodeRes.CODE_21016)
    private String address;
    @Schema(title = "验证码", required = true)
    @NotNull(CodeRes.CODE_18005)
    @Length(min = 6, max = 6, message = CodeRes.CODE_18005)
    private String code;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
