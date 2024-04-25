package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.ChainTypeEnum;

import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;

@Entity("appDiscoverDapp")
@Table("wal_app_discover_dapp")
@TableDef(comment = "新版发现dapp内容表")
public class AppDiscoverDapp extends DiscoverBase {

    private static final long serialVersionUID = 1L;

    public AppDiscoverDapp() {
    }

    @ColumnDef(comment = "公链类型")
    private ChainTypeEnum chainType;
    @ColumnDef(comment = "跳转链接")
    private String url;
    
    @ColumnDef(comment = "平台支持(ios/android)")
    private String[] platformSupports;

    public ChainTypeEnum getChainType() {
        return chainType;
    }

    public void setChainType(ChainTypeEnum chainType) {
        this.chainType = chainType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

	public String[] getPlatformSupports() {
		return platformSupports;
	}

	public void setPlatformSupports(String[] platformSupports) {
		this.platformSupports = platformSupports;
	}
    
}
