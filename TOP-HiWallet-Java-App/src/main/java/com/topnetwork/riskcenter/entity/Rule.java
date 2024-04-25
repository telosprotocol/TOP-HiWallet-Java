package com.topnetwork.riskcenter.entity;

import com.base.core.framework.sql.entity.BaseExt;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;

@Entity("rule")
@Table("sys_rule")
@TableDef(comment="规则")
public class Rule extends BaseExt {

	private static final long serialVersionUID = 1L;
	
	public Rule(){}

	@ColumnDef(comment="Bean")
	private String bean;

	@ColumnDef(comment="是否禁用")
	private Boolean disable;

	@ColumnDef(comment="备注")
	private String remark;

	public String getBean() {
		return bean;
	}

	public void setBean(String bean) {
		this.bean = bean;
	}

	public Boolean getDisable() {
		return disable;
	}

	public void setDisable(Boolean disable) {
		this.disable = disable;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
