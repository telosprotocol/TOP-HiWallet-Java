package com.topnetwork.wallet.result.wallet.discover;

import com.base.core.head.converter.UploadKeyConverterEditor;
import com.topnetwork.wallet.common.enums.LanguageEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.converter.PropertyConverter;

/**
 * @ClassName BannerI18nResult
 * @Description
 * @Author bran
 * @Date 2020/5/25 14:43
 */
public class BannerI18nResult {

    @Schema(title="banner图片url")
    @PropertyConverter(UploadKeyConverterEditor.class)
    private String imageUrl;
    @Schema(title="有效时间开始时间戳")
    private Long startTime;
    @Schema(title="有效时间结束时间戳")
    private Long endTime;
    @Schema(title="语言（CN,EN,KR,JP,VN...）")
    private LanguageEnum language;
    @Schema(title="国际化id")
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
