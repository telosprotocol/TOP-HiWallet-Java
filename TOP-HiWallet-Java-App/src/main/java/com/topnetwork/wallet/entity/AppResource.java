package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.RegionEnum;
import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;

import java.util.Date;

@Entity("appResource")
@Table("wal_app_resource")
@TableDef(comment = "app资源表")
public class AppResource extends Base {

    private static final long serialVersionUID = 1L;

    public AppResource() {
    }

    @ColumnDef(comment = "资源名称")
    private String name;

    @ColumnDef(comment = "下载链接")
    private String url;

    @ColumnDef(comment = "创建时间")
    private Date createTime;

    @ColumnDef(comment = "地区")
    private RegionEnum region;

    @ColumnDef(comment = "创建人id")
    private Long createId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public RegionEnum getRegion() {
        return region;
    }

    public void setRegion(RegionEnum region) {
        this.region = region;
    }
}
