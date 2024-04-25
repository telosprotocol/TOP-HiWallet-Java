package com.topnetwork.filecoin.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.filecoin.dao.TempDataDao;
import com.topnetwork.filecoin.entity.TempData;

@Repository("tempDataDao")
public class TempDataDaoImpl extends SqlBaseDaoImpl<TempData,Long>implements TempDataDao {

	public TempDataDaoImpl() {
		super(TempData.class);
	}

}
