package com.topnetwork.wyre.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wyre.dao.WyreCountryCoinDao;
import com.topnetwork.wyre.entity.WyreCountryCoin;

@Repository("wyreCountryCoinDao")
public class WyreCountryCoinDaoImpl extends SqlBaseDaoImpl<WyreCountryCoin,Long>implements WyreCountryCoinDao {

	public WyreCountryCoinDaoImpl() {
		super(WyreCountryCoin.class);
	}

}
