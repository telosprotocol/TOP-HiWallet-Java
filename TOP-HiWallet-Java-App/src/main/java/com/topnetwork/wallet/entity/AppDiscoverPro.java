package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.discover.BannerNewType;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;

@Entity("appDiscoverPro")
@Table("wal_app_discover_pro")
@TableDef(comment = "新版发现专业内容表")
public class AppDiscoverPro extends DiscoverBase {

    private static final long serialVersionUID = 1L;

    public AppDiscoverPro() {
    }

    @ColumnDef(comment = "跳转链接", isNull = true)
    private String url;
    @ColumnDef(comment = "区分新青年与staking")
    private BannerNewType proType;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BannerNewType getProType() {
        return proType;
    }

    public void setProType(BannerNewType proType) {
        this.proType = proType;
    }
}
