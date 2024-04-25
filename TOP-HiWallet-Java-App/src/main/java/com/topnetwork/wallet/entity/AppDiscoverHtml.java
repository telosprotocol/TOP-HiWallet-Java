package com.topnetwork.wallet.entity;

import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;

@Entity("appDiscoverHtml")
@Table("wal_app_discover_html")
public class AppDiscoverHtml extends DiscoverBase {

    private static final long serialVersionUID = 1L;

    public AppDiscoverHtml() {
    }

    @ColumnDef(comment = "跳转链接")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
