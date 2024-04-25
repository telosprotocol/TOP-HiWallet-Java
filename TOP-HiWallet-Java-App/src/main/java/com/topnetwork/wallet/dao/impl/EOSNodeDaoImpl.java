package com.topnetwork.wallet.dao.impl;

import com.topnetwork.wallet.dao.EOSNodeDao;
import com.topnetwork.wallet.entity.EOSNode;
import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

@Repository("eosNodeDao")
public class EOSNodeDaoImpl extends SqlBaseDaoImpl<EOSNode,Long>implements EOSNodeDao {

	public EOSNodeDaoImpl() {
		super(EOSNode.class);
	}

}
