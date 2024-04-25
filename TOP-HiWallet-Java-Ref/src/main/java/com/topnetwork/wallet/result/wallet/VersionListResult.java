package com.topnetwork.wallet.result.wallet;

import java.util.Date;

import com.topnetwork.wallet.common.enums.PlatformEnum;
import com.topnetwork.wallet.common.enums.RegionEnum;
import com.topnetwork.wallet.common.enums.VersionStatus;

import io.swagger.v3.oas.annotations.media.Schema;

public class VersionListResult {

    @Schema(title = "id", required = true)
    private Long id;
    @Schema(title = "最新版本", required = true)
    private String version;
    @Schema(title = "最新版本描述", required = true)
    private String description;
    @Schema(title = "最新版本英文描述", required = true)
    private String englishDescription;
    @Schema(title = "下载链接", required = true)
    private String downloadUrl;
    @Schema(title = "创建时间", required = true)
    private Date createTime;
    @Schema(title = "创建人ID", required = true)
    private Long createId;
    @Schema(title = "创建人名字", required = true)
    private String userName;
    @Schema(title = "平台", required = true)
    private PlatformEnum platform;

    @Schema(title = "是提示低版本更新到此版本")
    private Boolean canUpdate;

    @Schema(title = "staking开关")
    private Boolean staking;

    @Schema(title = "discover开关")
    private Boolean discover;

    @Schema(title = "地域（海外，国内）")
    private RegionEnum region;

    @Schema(title = "标题")
    private String title;

    @Schema(title = "英文标题")
    private String englishTitle;

    @Schema(title = "此版本更新到更高版本时更新类型")
    private VersionStatus type;
    @Schema(title = "是否通过审核（true：是；false：否）")
    private Boolean approved;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public PlatformEnum getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformEnum platform) {
        this.platform = platform;
    }

    public String getEnglishDescription() {
        return englishDescription;
    }

    public void setEnglishDescription(String englishDescription) {
        this.englishDescription = englishDescription;
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

    public RegionEnum getRegion() {
        return region;
    }

    public void setRegion(RegionEnum region) {
        this.region = region;
    }

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

    public VersionStatus getType() {
        return type;
    }

    public void setType(VersionStatus type) {
        this.type = type;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
