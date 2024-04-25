package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.InviteStatusEnum;
import com.gitee.magic.framework.head.converter.Base64ConverterEditor;
import com.base.core.framework.sql.entity.BaseExt;
import com.gitee.magic.core.converter.PropertyConverter;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.IndexesUnion;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Unique;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.UniqueUnion;

import java.util.Date;

@Entity("appInvite")
@Table("wal_app_invite")
@TableDef(comment = "App邀请记录表",indexes=@IndexesUnion(
        unique ={@UniqueUnion(fields={"countryCode","mobile"})}
))
public class AppInvite extends BaseExt {

    private static final long serialVersionUID = 1L;

    public AppInvite() {
    }

    @ColumnDef(comment = "邀请人uid", indexes = @Indexes(normal = @Normal))
    private String uid;
    @ColumnDef(indexes = @Indexes(unique = @Unique), length = 32, comment = "被邀请人手机")
    private String mobile;
    @ColumnDef(comment = "被邀请人国家码")
    private Integer countryCode;
    @ColumnDef(comment = "被邀请人微信名字", isNull = true)
    @PropertyConverter(Base64ConverterEditor.class)
    private String name;
    @ColumnDef(comment = "被邀请人微信头像地址", isNull = true)
    private String iconUrl;
    @ColumnDef(comment = "被邀请人微信openid", indexes = @Indexes(unique = @Unique), isNull = true)
    private String openid;
    @ColumnDef(comment = "被邀请人状态（未绑定：unBind，已绑定：bind）")
    private InviteStatusEnum inviteStatus;
    @ColumnDef(comment = "是否有效")
    private Boolean effective;
    @ColumnDef(comment = "创建时间")
    private Date createTime;

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public InviteStatusEnum getInviteStatus() {
        return inviteStatus;
    }

    public void setInviteStatus(InviteStatusEnum inviteStatus) {
        this.inviteStatus = inviteStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getEffective() {
        return effective;
    }

    public void setEffective(Boolean effective) {
        this.effective = effective;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
