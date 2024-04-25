package com.topnetwork.wallet.param.wallet.verifycode;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

import java.math.BigDecimal;

/**
 * @program: topverificationcode
 * @description: 验证参数
 * @author: Tyrone
 * @create: 2020-03-13 14:22
 **/
public class VerifyParam {
    @Schema(title = "安全Code", required = true)
    @NotNull(CodeRes.CODE_16002)
    @Length(message = CodeRes.CODE_16002)
    private String code;


    @Schema(title = "x轴坐标比例", required = true)
    @NotNull(CodeRes.CODE_16001)
    @Length(message = CodeRes.CODE_16001)
    private BigDecimal xaxis;

    @Schema(title = "y轴坐标比例", required = true)
    @NotNull(CodeRes.CODE_16007)
    @Length(message = CodeRes.CODE_16007)
    private BigDecimal yaxis;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getXaxis() {
        return xaxis;
    }

    public void setXaxis(BigDecimal xaxis) {
        this.xaxis = xaxis;
    }

    public BigDecimal getYaxis() {
        return yaxis;
    }

    public void setYaxis(BigDecimal yaxis) {
        this.yaxis = yaxis;
    }

    @Override
    public String toString() {
        return "VerifyParam{" +
                "code='" + code + '\'' +
                ", xaxis=" + xaxis +
                ", yaxis=" + yaxis +
                '}';
    }
}
