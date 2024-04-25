package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.discover.DiscoverDataFrom;
import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;

import java.util.Date;

@Entity("appDiscoverGameClassify")
@Table("wal_app_discover_game_classify")
public class AppDiscoverGameClassify extends Base {

    private static final long serialVersionUID = 1L;

    public AppDiscoverGameClassify() {
    }

    @ColumnDef(comment = "标题")
    private String title;
    @ColumnDef(comment = "英文标题")
    private String englishTitle;
    @ColumnDef(comment = "数据来源（数据存储位置）数据存储进redis： redis,数据存储在mysql：mysql")
    private DiscoverDataFrom dataFrom;
    @ColumnDef(comment = "是否显示更多（全部）")
    private Boolean showMore;
    @ColumnDef(comment = "最小接口支持版本")
    private Integer minVersion;
    @ColumnDef(comment = "最大接口版本")
    private Integer maxVersion;
    @ColumnDef(comment = "创建时间")
    private Date createTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEnglishTitle() {
        return englishTitle;
    }

    public void setEnglishTitle(String englishTitle) {
        this.englishTitle = englishTitle;
    }

    public DiscoverDataFrom getDataFrom() {
        return dataFrom;
    }

    public void setDataFrom(DiscoverDataFrom dataFrom) {
        this.dataFrom = dataFrom;
    }

    public Boolean getShowMore() {
        return showMore;
    }

    public void setShowMore(Boolean showMore) {
        this.showMore = showMore;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getMinVersion() {
        return minVersion;
    }

    public void setMinVersion(Integer minVersion) {
        this.minVersion = minVersion;
    }

    public Integer getMaxVersion() {
        return maxVersion;
    }

    public void setMaxVersion(Integer maxVersion) {
        this.maxVersion = maxVersion;
    }
}
