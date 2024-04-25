package com.topnetwork.wallet.result.wallet.discover;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @ClassName ProI18nResult
 * @Description
 * @Author bran
 * @Date 2020/5/26 11:35
 */
public class ProI18nResult {

    @Schema(title="国际化id")
    private Long i18nId;
    @Schema(title="标题")
    private String title;
    @Schema(title="副标题")
    private String subTitle;
    @Schema(title="语言")
    private LanguageEnum language;
    @Schema(title="所属分组信息")
    private List<ProI18nGroupResult> groups;

    public Long getI18nId() {
        return i18nId;
    }

    public void setI18nId(Long i18nId) {
        this.i18nId = i18nId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }

    public List<ProI18nGroupResult> getGroups() {
        return groups;
    }

    public void setGroups(List<ProI18nGroupResult> groups) {
        this.groups = groups;
    }
}
