package com.topnetwork.wallet.param.wallet.discover;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.*;
import com.gitee.magic.core.valid.annotation.Enum;

import java.util.List;

/**
 * @ClassName ProI18nParam
 * @Description
 * @Author bran
 * @Date 2020/5/26 13:42
 */
public class ProI18nParam {

    @Schema(title = "标题", required = true)
    @NotNull(CodeRes.CODE_20016)
    @Length(max = 100, message = CodeRes.CODE_20016)
    private String title;
    @Schema(title = "副标题", required = true)
    @NotNull(CodeRes.CODE_20017)
    @Length(max = 100, message = CodeRes.CODE_20017)
    private String subTitle;
    @Schema(title = "语言（CN,EN,KR,JP,VN...）", required = true)
    @Enum(CodeRes.CODE_14011)
    @NotNull(CodeRes.CODE_14011)
    @Length(message = CodeRes.CODE_14011)
    private LanguageEnum language;
    @Schema(title = "国际化id", required = false)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_20009)
    private Long i18nId;
    @Schema(title = "分组信息", required = true)
    @NotNull(CodeRes.CODE_20023)
    @NotEmpty(CodeRes.CODE_20023)
    private List<GroupI18nParam> groups;

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

    public Long getI18nId() {
        return i18nId;
    }

    public void setI18nId(Long i18nId) {
        this.i18nId = i18nId;
    }

    public List<GroupI18nParam> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupI18nParam> groups) {
        this.groups = groups;
    }
}
