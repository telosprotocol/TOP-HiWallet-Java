package com.topnetwork.wallet.entity;

import java.util.Date;

import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ScriptConverter;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.converter.FieldLongText;

@Entity("coinSpecies")
@Table("wal_coin_species")
@TableDef(comment = "币分类")
public class CoinSpecies extends Base {

    private static final long serialVersionUID = 1L;

    public CoinSpecies() {
    }

    @ColumnDef(comment = "中文名称")
    private String chineseName;

    @ColumnDef(comment = "英文名称", indexes = @Indexes(normal = @Normal))
    private String englishName;

    @ColumnDef(comment = "昵称", indexes = @Indexes(normal = @Normal))
    private String nickname;

    @ColumnDef(comment = "合约地址", isNull = true, indexes = @Indexes(normal = @Normal))
    private String contractAddress;

    @ColumnDef(comment = "logo地址", isNull = true)
    private String logoUrl;

    @ColumnDef(comment = "Token主页地址", isNull = true)
    private String tokenDescriptionUrl;

    @ColumnDef(comment = "中文描述")
    @ScriptConverter(FieldLongText.class)
    private String chineseDescription;

    @ColumnDef(comment = "英文描述")
    @ScriptConverter(FieldLongText.class)
    private String englishDescription;

    @ColumnDef(comment = "是否热门", indexes = @Indexes(normal = @Normal))
    private Integer isHot;

    @ColumnDef(comment = "级别", indexes = @Indexes(normal = @Normal))
    private Integer level;

    @ColumnDef(comment = "是否隐藏")
    private Integer isHide;

    @ColumnDef(comment = "创建时间")
    private Date createTime;

    @ColumnDef(comment = "更新时间", isNull = true)
    private Date updateTime;

    @ColumnDef(comment = "币种精度", isNull = true)
    private Integer tokenDecimals;

    @ColumnDef(comment = "显示精度", isNull = true)
    private Integer showDecimals;

    @ColumnDef(comment = "父ID", isNull = true)
    private Long fatherId;

    @ColumnDef(comment = "排序")
    private Integer tokenOrder;

    @ColumnDef(comment = "创建人id", isNull = true)
    private Long createId;

    @ColumnDef(comment = "更新人id", isNull = true)
    private Long updateId;

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getTokenDecimals() {
        return tokenDecimals;
    }

    public void setTokenDecimals(Integer tokenDecimals) {
        this.tokenDecimals = tokenDecimals;
    }

    public Integer getShowDecimals() {
        return showDecimals;
    }

    public void setShowDecimals(Integer showDecimals) {
        this.showDecimals = showDecimals;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getTokenDescriptionUrl() {
        return tokenDescriptionUrl;
    }

    public void setTokenDescriptionUrl(String tokenDescriptionUrl) {
        this.tokenDescriptionUrl = tokenDescriptionUrl;
    }

    public String getEnglishDescription() {
        return englishDescription;
    }

    public void setEnglishDescription(String englishDescription) {
        this.englishDescription = englishDescription;
    }

    public String getChineseDescription() {
        return chineseDescription;
    }

    public void setChineseDescription(String chineseDescription) {
        this.chineseDescription = chineseDescription;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getIsHide() {
        return isHide;
    }

    public void setIsHide(Integer isHide) {
        this.isHide = isHide;
    }

    public Long getFatherId() {
        return fatherId;
    }

    public void setFatherId(Long fatherId) {
        this.fatherId = fatherId;
    }

    public Integer getTokenOrder() {
        return tokenOrder;
    }

    public void setTokenOrder(Integer tokenOrder) {
        this.tokenOrder = tokenOrder;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
