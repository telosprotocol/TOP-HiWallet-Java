package com.topnetwork.wallet.entity;

import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;

import java.util.Date;

@Entity("appHelpCategory")
@Table("wal_appHelpCategory")
@TableDef(comment = "App帮助类型")
public class AppHelpCategory extends Base {

	private static final long serialVersionUID = 1L;

	public AppHelpCategory() {

	}

	@ColumnDef(comment = "名称")
	private String name;

	@ColumnDef(comment = "英语名称")
	private String englishName;

	@ColumnDef(comment = "等级",indexes = @Indexes(normal = @Normal))
	private Integer level;

	@ColumnDef(comment = "上级id",isNull=true)
	private Long fatherId;

	@ColumnDef(comment = "创建时间")
	private Date createTime;

	@ColumnDef(comment = "更新时间")
	private Date updateTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getFatherId() {
		return fatherId;
	}

	public void setFatherId(Long fatherId) {
		this.fatherId = fatherId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
}