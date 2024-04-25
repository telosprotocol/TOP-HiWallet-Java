package com.topnetwork.wallet.param.wallet.discover;

import com.base.core.head.valid.UploadKeyValid;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.valid.RegexStr;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Custom;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotEmpty;
import com.gitee.magic.core.valid.annotation.NotNull;
import com.gitee.magic.core.valid.annotation.Regex;

/**
 * @ClassName AddHtmlParam
 * @Description
 * @Author bran
 * @Date 2020/5/26 15:29
 */
public class AddHtmlParam {

    @Schema(title = "应用icon", required = true)
    @NotEmpty(CodeRes.CODE_20011)
    @Custom(value = UploadKeyValid.class, message = CodeRes.CODE_20011)
    private String imageUrl;
    @Schema(title = "是否显示", required = true)
    @NotNull(CodeRes.CODE_20012)
    @Length(message = CodeRes.CODE_20012)
    @Format(type = Format.FormatType.BOOLEAN, message = CodeRes.CODE_20012)
    private Boolean appShow;
    @Schema(title = "应用备注标题", required = true)
    @NotEmpty(CodeRes.CODE_20013)
    @Length(max = 100, message = CodeRes.CODE_20013)
    private String title;
    @Schema(title = "跳转链接", required = true)
    @Length(max = 100, message = CodeRes.CODE_19001)
    @Regex(regex = RegexStr.HTTP, message = CodeRes.CODE_19001)
    @NotNull(CodeRes.CODE_19001)
    private String url;


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getAppShow() {
        return appShow;
    }

    public void setAppShow(Boolean appShow) {
        this.appShow = appShow;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
