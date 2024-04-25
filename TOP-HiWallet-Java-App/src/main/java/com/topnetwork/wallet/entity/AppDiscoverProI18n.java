package com.topnetwork.wallet.entity;

import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.IndexesUnion;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.UniqueUnion;

@Entity("appDiscoverProI18n")
@Table("wal_app_discover_pro_i18n")
@TableDef(comment = "专业应用 I18n表", indexes = @IndexesUnion(
		unique = {@UniqueUnion(fields = {"appId", "language"})}
))
public class AppDiscoverProI18n extends DiscoverI18nBase {

	private static final long serialVersionUID = 1L;
	
	public AppDiscoverProI18n(){}

}
