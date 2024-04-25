package com.topnetwork.wyre.entity;

import com.base.core.framework.sql.entity.BaseExt;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Unique;

@Entity("wyreCountry")
@Table("wal_wyre_country")
@TableDef(comment = "法币支持国家")
public class WyreCountry extends BaseExt {

    private static final long serialVersionUID = 1L;

    public WyreCountry() {
    }

    @ColumnDef(comment = "国家名称")
    private String name;
    @ColumnDef(comment = "国家简称", indexes = @Indexes(unique = @Unique))
    private String countryCode;
    @ColumnDef(comment = "国家币种简称", indexes = @Indexes(unique = @Unique))
    private String currencies;
    @ColumnDef(comment = "国家币种图标")
    private String currenciesIcon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCurrencies() {
        return currencies;
    }

    public void setCurrencies(String currencies) {
        this.currencies = currencies;
    }

    public String getCurrenciesIcon() {
        return currenciesIcon;
    }

    public void setCurrenciesIcon(String currenciesIcon) {
        this.currenciesIcon = currenciesIcon;
    }
}
