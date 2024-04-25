package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppNoticeDao;
import com.topnetwork.wallet.entity.AppNotice;

@Repository("appNoticeDao")
public class AppNoticeDaoImpl extends SqlBaseDaoImpl<AppNotice,Long>implements AppNoticeDao {

	public AppNoticeDaoImpl() {
		super(AppNotice.class);
	}

}
