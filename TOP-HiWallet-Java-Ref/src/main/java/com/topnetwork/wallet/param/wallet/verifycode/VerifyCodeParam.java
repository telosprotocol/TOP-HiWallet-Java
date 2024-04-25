package com.topnetwork.wallet.param.wallet.verifycode;

import com.base.core.head.valid.UploadKeyValid;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.verify.VerifyCodeStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Custom;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;


/**
 * @program: topverificationcode
 * @description:
 * @author: Tyrone
 * @create: 2020-03-16 17:15
 **/
public class VerifyCodeParam {

    @Schema(title = "imgKey(缩略图地址)", required = true)
    @NotNull(CodeRes.CODE_16003)
    @Format(type = Format.FormatType.MD5, message = CodeRes.CODE_16003)
    @Custom(value=UploadKeyValid.class, message = CodeRes.CODE_16003)
    private String imgKey;

    @Schema(title = "缩略图名称", required = true)
    @NotNull(CodeRes.CODE_16004)
    @Length(max = 100, message = CodeRes.CODE_16004)
    private String name;

    @Schema(title = "是否启用", required = true)
    @NotNull(CodeRes.CODE_11005)
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
