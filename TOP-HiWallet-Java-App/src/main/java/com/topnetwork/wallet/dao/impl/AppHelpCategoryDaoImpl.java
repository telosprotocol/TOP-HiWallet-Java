package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppHelpCategoryDao;
import com.topnetwork.wallet.entity.AppHelpCategory;

@Repository("appHelpCategoryDao")
public class AppHelpCategoryDaoImpl extends SqlBaseDaoImpl<AppHelpCategory,Long>implements AppHelpCategoryDao {

	public AppHelpCategoryDaoImpl() {
		super(AppHelpCategory.class);
	}

}
