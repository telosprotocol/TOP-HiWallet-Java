package com.topnetwork.wallet.param.wallet;

import com.topnetwork.wallet.common.CodeRes;

import com.topnetwork.wallet.common.enums.PlatformEnum;
import com.topnetwork.wallet.common.enums.RegionEnum;
import com.topnetwork.wallet.common.enums.VersionManagerStatus;
import com.topnetwork.wallet.common.enums.VersionStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

public class VersionAddParam {

    @Schema(title = "平台（IOS；ANDROID）", required = true)
    @NotNull(CodeRes.CODE_14018)
    @Enum(CodeRes.CODE_14018)
    private PlatformEnum platform;

    @Schema(title = "版本", required = true)
    @NotNull(CodeRes.CODE_14019)
    private String version;

    @Schema(title = "标题", required = true)
    @NotNull(CodeRes.CODE_15064)
    @Length(message = CodeRes.CODE_15064)
    private String title;

    @Schema(title = "英文标题", required = true)
    @NotNull(CodeRes.CODE_15064)
    @Length(message = CodeRes.CODE_15064)
    private String englishTitle;

    @Schema(title = "版本描述", required = true)
    @NotNull(CodeRes.CODE_14020)
    private String description;

    @Schema(title = "英文版本描述", required = true)
    @NotNull(CodeRes.CODE_14020)
    private String englishDescription;

    @Schema(title = "是否强制更新", required = true)
    @NotNull(CodeRes.CODE_14021)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_14015)
    private Integer isForcedUpdates;

    @Schema(title = "下载链接", required = true)
    @NotNull(CodeRes.CODE_14022)
    private String downloadUrl;

    @Schema(title = "此版本更新到更高版本时更新类型")
    @NotNull(CodeRes.CODE_15067)
    @Enum(CodeRes.CODE_15067)
    private VersionManagerStatus type;

    @Schema(title = "是提示低版本更新到此版本", required = true)
    @NotNull(CodeRes.CODE_15079)
    @Format(type = Format.FormatType.BOOLEAN, message = CodeRes.CODE_15079)
    private Boolean canUpdate;

    @Schema(title = "staking开关", required = true)
    @NotNull(CodeRes.CODE_15080)
    @Format(type = Format.FormatType.BOOLEAN, message = CodeRes.CODE_15080)
    private Boolean staking;

    @Schema(title = "discover开关", required = true)
    @NotNull(CodeRes.CODE_15081)
    @Format(type = Format.FormatType.BOOLEAN, message = CodeRes.CODE_15081)
    private Boolean discover;

    @Schema(title = "是否通过审核（true：是；false：否）", required = true)
    @NotNull(CodeRes.CODE_15078)
    @Format(type = Format.FormatType.BOOLEAN, message = CodeRes.CODE_15078)
    private Boolean approved;

    @Schema(title = "地域（海外，国内）", required = true)
    @NotNull(CodeRes.CODE_15013)
    @Enum(CodeRes.CODE_15013)
    private RegionEnum region;


    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
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

    public VersionManagerStatus getType() {
        return type;
    }

    public void setType(VersionManagerStatus type) {
        this.type = type;
    }

    public PlatformEnum getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformEnum platform) {
        this.platform = platform;
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

    public Integer getIsForcedUpdates() {
        return isForcedUpdates;
    }

    public void setIsForcedUpdates(Integer isForcedUpdates) {
        this.isForcedUpdates = isForcedUpdates;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
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
}
