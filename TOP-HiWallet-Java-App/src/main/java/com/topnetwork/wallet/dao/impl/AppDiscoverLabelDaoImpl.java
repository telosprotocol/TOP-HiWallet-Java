package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppDiscoverLabelDao;
import com.topnetwork.wallet.entity.AppDiscoverLabel;

@Repository("appDiscoverLabelDao")
public class AppDiscoverLabelDaoImpl extends SqlBaseDaoImpl<AppDiscoverLabel,Long>implements AppDiscoverLabelDao {

	public AppDiscoverLabelDaoImpl() {
		super(AppDiscoverLabel.class);
	}

}
