package com.topnetwork.wyre.entity;

import com.base.core.framework.sql.entity.BaseExt;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Unique;

@Entity("wyreCountryCoin")
@Table("wyr_wyre_country_coin")
@TableDef(comment = "国家支持兑换币种关系表")
public class WyreCountryCoin extends BaseExt {

    private static final long serialVersionUID = 1L;

    public WyreCountryCoin() {
    }

    @ColumnDef(comment = "国家id", indexes = @Indexes(normal = @Normal))
    private Long countryId;
    @ColumnDef(comment = "国家支持币种Id", indexes = @Indexes(normal = @Normal))
    private Long coinId;

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getCoinId() {
        return coinId;
    }

    public void setCoinId(Long coinId) {
        this.coinId = coinId;
    }
}
