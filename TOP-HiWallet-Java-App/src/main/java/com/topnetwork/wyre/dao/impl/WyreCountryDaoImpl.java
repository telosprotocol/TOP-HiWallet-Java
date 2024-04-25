package com.topnetwork.wyre.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wyre.dao.WyreCountryDao;
import com.topnetwork.wyre.entity.WyreCountry;

@Repository("wyreCountryDao")
public class WyreCountryDaoImpl extends SqlBaseDaoImpl<WyreCountry,Long>implements WyreCountryDao {

	public WyreCountryDaoImpl() {
		super(WyreCountry.class);
	}

}
