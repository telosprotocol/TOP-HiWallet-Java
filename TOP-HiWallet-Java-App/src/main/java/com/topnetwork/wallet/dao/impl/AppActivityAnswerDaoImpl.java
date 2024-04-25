package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.AppActivityAnswerDao;
import com.topnetwork.wallet.entity.AppActivityAnswer;

@Repository("appActivityAnswerDao")
public class AppActivityAnswerDaoImpl extends SqlBaseDaoImpl<AppActivityAnswer,Long>implements AppActivityAnswerDao {

	public AppActivityAnswerDaoImpl() {
		super(AppActivityAnswer.class);
	}

}
