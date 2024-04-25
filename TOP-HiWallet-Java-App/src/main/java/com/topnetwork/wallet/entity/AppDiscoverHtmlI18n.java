package com.topnetwork.wallet.entity;

import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.IndexesUnion;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ScriptConverter;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.UniqueUnion;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.converter.FieldLongText;

@Entity("appDiscoverHtmlI18n")
@Table("wal_app_discover_html_i18n")
@TableDef(comment = "html I18n表", indexes = @IndexesUnion(
        unique = {@UniqueUnion(fields = {"appId", "language"})}
))
public class AppDiscoverHtmlI18n extends DiscoverI18nBase {

    private static final long serialVersionUID = 1L;

    public AppDiscoverHtmlI18n() {
    }

    @ColumnDef(comment = "简介")
    private String description;
    @ColumnDef(comment = "详细介绍")
    @ScriptConverter(FieldLongText.class)
    private String detail;
    @ColumnDef(comment = "图片介绍", isNull = true)
    @ScriptConverter(FieldLongText.class)
    private String[] imageUrl;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
