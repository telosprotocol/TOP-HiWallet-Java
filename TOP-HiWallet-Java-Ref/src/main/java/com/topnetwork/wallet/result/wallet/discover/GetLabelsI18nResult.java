package com.topnetwork.wallet.result.wallet.discover;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName GetLabelsResult
 * @Description
 * @Author bran
 * @Date 2020/5/19 17:32
 */
public class GetLabelsI18nResult {

    @Schema(title="标签语言id")
    private Long i18nId;
    @Schema(title="标签语言")
    private LanguageEnum language;
    @Schema(title="标签语言下名称")
    private String name;

    public Long getI18nId() {
        return i18nId;
    }

    public void setI18nId(Long i18nId) {
        this.i18nId = i18nId;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
