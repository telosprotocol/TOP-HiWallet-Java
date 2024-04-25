package com.topnetwork.wallet.param.wallet.discover;

import com.base.core.head.valid.UploadKeyValid;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.discover.AppType;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Custom;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName UpdateProAppParam
 * @Description
 * @Author bran
 * @Date 2020/5/26 11:13
 */
public class UpdateProAppParam {

    @Schema(title = "app类型 PRO;HTML;DAPP;APP", required = true)
    @Enum(CodeRes.CODE_20010)
    @NotNull(CodeRes.CODE_20010)
    @Length(message = CodeRes.CODE_20010)
    private AppType type;
    @Schema(title = "应用icon", required = true)
    @Custom(value = UploadKeyValid.class ,message = CodeRes.CODE_20011)
    @NotNull(CodeRes.CODE_20011)
    @Length(message = CodeRes.CODE_20011)
    private String imageUrl;
    @Schema(title = "是否显示", required = true)
    @NotNull(CodeRes.CODE_20012)
    @Length(message = CodeRes.CODE_20012)
    @Format(type = Format.FormatType.BOOLEAN, message = CodeRes.CODE_20012)
    private Boolean appShow;
    @Schema(title = "应用备注标题", required = true)
    @NotNull(CodeRes.CODE_20013)
    @Length(max = 100, message = CodeRes.CODE_20013)
    private String title;
    @Schema(title = "app id", required = true)
    @NotNull(CodeRes.CODE_20014)
    @Length(message = CodeRes.CODE_20014)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_20014)
    private Long id;

    public AppType getType() {
        return type;
    }

    public void setType(AppType type) {
        this.type = type;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
