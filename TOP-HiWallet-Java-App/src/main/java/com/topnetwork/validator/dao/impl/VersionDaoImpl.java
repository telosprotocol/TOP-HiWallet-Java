package com.topnetwork.validator.dao.impl;

import org.springframework.stereotype.Repository;

import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;
import com.topnetwork.validator.dao.VersionDao;
import com.topnetwork.validator.entity.Version;

@Repository("versionDao")
public class VersionDaoImpl extends SqlBaseDaoImpl<Version,Long>implements VersionDao {

	public VersionDaoImpl() {
		super(Version.class);
	}

}
