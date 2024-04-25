package com.topnetwork.filecoin.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.filecoin.dao.FiledRecordsDao;
import com.topnetwork.filecoin.entity.FiledRecords;

@Repository("filedRecordsDao")
public class FiledRecordsDaoImpl extends SqlBaseDaoImpl<FiledRecords,Long>implements FiledRecordsDao {

	public FiledRecordsDaoImpl() {
		super(FiledRecords.class);
	}

}
