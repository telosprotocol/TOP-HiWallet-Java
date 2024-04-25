package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.discover.LabelType;
import com.base.core.framework.sql.entity.BaseExt;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;

@Entity("appDiscoverLabel")
@Table("wal_app_discover_label")
@TableDef(comment = "发现标签")
public class AppDiscoverLabel extends BaseExt {

	private static final long serialVersionUID = 1L;
	
	public AppDiscoverLabel(){}

	@ColumnDef(comment = "标签类型")
	private LabelType labelType;

	public LabelType getLabelType() {
		return labelType;
	}

	public void setLabelType(LabelType labelType) {
		this.labelType = labelType;
	}
}
