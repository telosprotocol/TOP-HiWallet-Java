package com.topnetwork.wallet.param.wallet.verifycode;

import com.base.core.head.ao.IDAO;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Length;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.verify.VerifyCodeStatus;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @program: topverificationcode
 * @description:
 * @author: Tyrone
 * @create: 2020-03-16 17:15
 **/
public class EditVerifyCodeParam extends IDAO {

    @Schema(title = "imgKey(缩略图地址)", required = true)
    @Length(max = 1000,message = CodeRes.CODE_16003)
    private String imgKey;

    @Schema(title = "缩略图名称", required = true)
    @Length(max = 100,message = CodeRes.CODE_16004)
    private String name;

    @Schema(title = "是否启用", required = true)
    @Enum(CodeRes.CODE_11005)
    private VerifyCodeStatus verifyCodeStatus;

    public String getImgKey() {
        return imgKey;
    }

    public void setImgKey(String imgKey) {
        this.imgKey = imgKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VerifyCodeStatus getVerifyCodeStatus() {
        return verifyCodeStatus;
    }

    public void setVerifyCodeStatus(VerifyCodeStatus verifyCodeStatus) {
        this.verifyCodeStatus = verifyCodeStatus;
    }

    @Override
    public String toString() {
        return "VerifyCodeParam{" +
                "imgKey='" + imgKey + '\'' +
                ", name='" + name + '\'' +
                ", verifyCodeStatus=" + verifyCodeStatus +
                '}';
    }
}
