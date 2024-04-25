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

import java.util.Date;

@Entity("appDiscoverBannerNewI18n")
@Table("wal_app_discover_banner_new_i18n")
@TableDef(comment = "新版banner I18n表", indexes = @IndexesUnion(
        unique = {@UniqueUnion(fields = {"bannerId", "language"})}
))
public class AppDiscoverBannerNewI18n extends BaseExt {

    private static final long serialVersionUID = 1L;

    public AppDiscoverBannerNewI18n() {
    }

    @ColumnDef(comment = "语言", indexes = @Indexes(normal = @Normal))
    private LanguageEnum language;
    @ColumnDef(comment = "banner id", indexes = @Indexes(normal = @Normal))
    private Long bannerId;
    @ColumnDef(comment = "banner图地址")
    private String imageUrl;
    @ColumnDef(comment = "banner生效开始时间")
    private Date startTime;
    @ColumnDef(comment = "banner生效结束时间")
    private Date endTime;

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }

    public Long getBannerId() {
        return bannerId;
    }

    public void setBannerId(Long bannerId) {
        this.bannerId = bannerId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
