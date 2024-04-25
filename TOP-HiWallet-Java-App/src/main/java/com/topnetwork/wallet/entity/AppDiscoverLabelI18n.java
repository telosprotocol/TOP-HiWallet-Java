package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.base.core.framework.sql.entity.BaseExt;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.IndexesUnion;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.UniqueUnion;

@Entity("appDiscoverLabelI18n")
@Table("wal_app_discover_label_i18n")
@TableDef(comment = "发现标签国际化",indexes=@IndexesUnion(
        unique ={@UniqueUnion(fields={"labelId","language"})}
))
public class AppDiscoverLabelI18n extends BaseExt {

    private static final long serialVersionUID = 1L;

    public AppDiscoverLabelI18n() {
    }

    @ColumnDef(comment = "标签id", indexes = @Indexes(normal = @Normal))
    private Long labelId;
    @ColumnDef(comment = "语言")
    private LanguageEnum language;
    @ColumnDef(comment = "标签名称")
    private String name;

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
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
