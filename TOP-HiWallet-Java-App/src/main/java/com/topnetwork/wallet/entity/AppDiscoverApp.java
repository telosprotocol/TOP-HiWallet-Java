package com.topnetwork.wallet.entity;

import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;

@Entity("appDiscoverApp")
@Table("wal_app_discover_app")
public class AppDiscoverApp extends DiscoverBase {

    private static final long serialVersionUID = 1L;

    public AppDiscoverApp() {
    }

    @ColumnDef(comment = "下载链接")
    private String url;
    @ColumnDef(comment = "备用下载链接")
    private String subUrl;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubUrl() {
        return subUrl;
    }

    public void setSubUrl(String subUrl) {
        this.subUrl = subUrl;
    }
}
