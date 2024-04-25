package com.topnetwork.wallet.entity;

import com.base.core.framework.sql.entity.BaseExt;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Unique;

@Entity("defiInvite")
@Table("wal_defi_invite")
@TableDef(comment = "defi 业务邀请码记录")
public class DefiInvite extends BaseExt {

	private static final long serialVersionUID = 1L;
	
	public DefiInvite(){}
	
	@ColumnDef(comment = "eth地址", indexes = @Indexes(unique = @Unique))
	private String ethAddress;
	
	@ColumnDef(comment = "当前地址的邀请码", indexes = @Indexes(unique = @Unique))
	private String inviteCode;
	
	@ColumnDef(comment = "地址绑定的好友邀请码", isNull = true ,indexes = @Indexes(normal = @Normal))
	private String bindInviteCode;

	public String getEthAddress() {
		return ethAddress;
	}

	public void setEthAddress(String ethAddress) {
		this.ethAddress = ethAddress;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getBindInviteCode() {
		return bindInviteCode;
	}

	public void setBindInviteCode(String bindInviteCode) {
		this.bindInviteCode = bindInviteCode;
	}
	
}
