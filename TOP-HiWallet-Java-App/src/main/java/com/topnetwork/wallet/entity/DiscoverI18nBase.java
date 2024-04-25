package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.base.core.framework.sql.entity.BaseExt;
import com.gitee.magic.jdbc.persistence.annotation.MappedSuperclass;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;

/**
 * @ClassName DiscoverI18nBase
 * @Description
 * @Author bran
 * @Date 2020/5/21 10:41
 */
@MappedSuperclass
public class DiscoverI18nBase extends BaseExt {

    private static final long serialVersionUID = 1L;

    @ColumnDef(comment = "语言", indexes = @Indexes(normal = @Normal))
    private LanguageEnum language;
    @ColumnDef(comment = "应用id", indexes = @Indexes(normal = @Normal))
    private Long appId;
    @ColumnDef(comment = "标题")
    private String title;
    @ColumnDef(comment = "副标题")
    private String subTitle;

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
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
}
