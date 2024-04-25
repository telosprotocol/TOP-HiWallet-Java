package com.topnetwork.wallet.param.wallet.discover;

import com.base.core.head.valid.UploadKeyValid;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Custom;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName BannerI18nResult
 * @Description
 * @Author bran
 * @Date 2020/5/25 14:43
 */
public class BannerI18nParam {

    @Schema(title = "banner图片url", required = true)
    @Custom(value = UploadKeyValid.class ,message = CodeRes.CODE_15052)
    @NotNull(CodeRes.CODE_15052)
    @Length(message = CodeRes.CODE_15052)
    private String imageUrl;
    @Schema(title = "有效时间开始时间戳", required = true)
    @NotNull(CodeRes.CODE_14006)
    @Length(message = CodeRes.CODE_14006)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_14006)
    private Long startTime;
    @Schema(title = "有效时间结束时间戳", required = true)
    @NotNull(CodeRes.CODE_14007)
    @Length(message = CodeRes.CODE_14007)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_14007)
    private Long endTime;
    @Schema(title = "语言（CN,EN,KR,JP,VN...）", required = true)
    @Enum(CodeRes.CODE_14011)
    @NotNull(CodeRes.CODE_14011)
    @Length(message = CodeRes.CODE_14011)
    private LanguageEnum language;
    @Schema(title = "国际化id", required = false)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_20009)
    private Long i18nId;


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }

    public Long getI18nId() {
        return i18nId;
    }

    public void setI18nId(Long i18nId) {
        this.i18nId = i18nId;
    }
}
