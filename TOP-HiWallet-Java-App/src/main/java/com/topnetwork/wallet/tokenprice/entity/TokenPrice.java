package com.topnetwork.wallet.tokenprice.entity;

import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;

@Entity("tokenPrice")
@Table("wal_token_price")
@TableDef(comment = "tokenView查币价第三方表")
public class TokenPrice extends Base {

    private static final long serialVersionUID = 1L;

    public TokenPrice() {
    }

    @ColumnDef(comment = "英文名称", indexes = @Indexes(normal = @Normal))
    private String englishName;
    @ColumnDef(comment = "非小号id", isNull = true)
    private String apiId;
    @ColumnDef(comment = "coingecko id", isNull = true)
    private String ids;
    @ColumnDef(comment = "对应币种id", indexes = @Indexes(normal = @Normal))
    private Long coinId;


    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Long getCoinId() {
        return coinId;
    }

    public void setCoinId(Long coinId) {
        this.coinId = coinId;
    }
}
