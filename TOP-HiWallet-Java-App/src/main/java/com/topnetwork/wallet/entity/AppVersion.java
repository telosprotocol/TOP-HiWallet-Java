package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.*;
import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ScriptConverter;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.converter.FieldLongText;

import java.util.Date;

@Entity("appVersion")
@Table("wal_appVersion")
@TableDef(comment = "App版本")
public class AppVersion extends Base {

    private static final long serialVersionUID = 1L;

    public AppVersion() {
    }

    @ColumnDef(comment = "版本号", indexes = @Indexes(normal = @Normal))
    private String version;

    @ColumnDef(comment = "标题", isNull = true)
    private String title;

    @ColumnDef(comment = "英文标题", isNull = true)
    private String englishTitle;

    @ColumnDef(comment = "下载链接")
    private String downloadUrl;

    @ScriptConverter(FieldLongText.class)
    @ColumnDef(comment = "描述")
    private String description;

    @ScriptConverter(FieldLongText.class)
    @ColumnDef(comment = "英文描述")
    private String englishDescription;

    @ColumnDef(comment = "平台：IOS,ANDROID")
    private PlatformEnum platform;

    @ColumnDef(comment = "地域（海外，国内）")
    private RegionEnum region;

    @ColumnDef(comment = "设备选用语言")
    private LanguageEnum language;

    @ColumnDef(comment = "此版本升级到更高版本时是否要强更 1、是；0；不是")
    private Integer isForcedUpdates;

    @ColumnDef(comment = "此版本更新到更高版本时更新类型")
    private VersionManagerStatus type;

    @ColumnDef(comment = "是提示低版本更新到此版本")
    private Boolean canUpdate;

    @ColumnDef(comment = "staking开关")
    private Boolean staking;

    @ColumnDef(comment = "discover开关")
    private Boolean discover;

    @ColumnDef(comment = "是否通过审核")
    private Boolean approved;

    @ColumnDef(comment = "创建时间")
    private Date createTime;

    @ColumnDef(comment = "创建人id")
    private Long createId;

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl == null ? null : downloadUrl.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public PlatformEnum getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformEnum platform) {
        this.platform = platform;
    }

    public Integer getIsForcedUpdates() {
        return isForcedUpdates;
    }

    public void setIsForcedUpdates(Integer isForcedUpdates) {
        this.isForcedUpdates = isForcedUpdates;
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

    public String getEnglishDescription() {
        return englishDescription;
    }

    public void setEnglishDescription(String englishDescription) {
        this.englishDescription = englishDescription;
    }


    public RegionEnum getRegion() {
        return region;
    }

    public void setRegion(RegionEnum region) {
        this.region = region;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }

    public Boolean getCanUpdate() {
        return canUpdate;
    }

    public void setCanUpdate(Boolean canUpdate) {
        this.canUpdate = canUpdate;
    }

    public Boolean getStaking() {
        return staking;
    }

    public void setStaking(Boolean staking) {
        this.staking = staking;
    }

    public Boolean getDiscover() {
        return discover;
    }

    public void setDiscover(Boolean discover) {
        this.discover = discover;
    }

    public VersionManagerStatus getType() {
        return type;
    }

    public void setType(VersionManagerStatus type) {
        this.type = type;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}