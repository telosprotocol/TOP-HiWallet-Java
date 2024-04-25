package com.topnetwork.wallet.param.wallet.discover;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.valid.S3KeyArrayValid;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Custom;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotEmpty;


/**
 * @ClassName ProI18nParam
 * @Description
 * @Author bran
 * @Date 2020/5/26 13:42
 */
public class DappI18nParam extends ProI18nParam {

    @Schema(title = "简介", required = true)
    @NotEmpty(CodeRes.CODE_20020)
    @Length(max = 100, message = CodeRes.CODE_20020)
    private String desc;
    @Schema(title = "详细描述", required = true)
    @NotEmpty(CodeRes.CODE_20021)
    @Length(max = 99999, message = CodeRes.CODE_20021)
    private String detail;
    @Schema(title = "图片描述", required = false)
    @Format(type= Format.FormatType.JSONArray,message = CodeRes.CODE_20022)
    @Custom(value = S3KeyArrayValid.class ,message = CodeRes.CODE_20022)
    private String[] imageUrl;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String[] getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String[] imageUrl) {
        this.imageUrl = imageUrl;
    }

}
