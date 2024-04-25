package com.topnetwork.filecoin.service;

import com.topnetwork.wallet.param.filecoin.AddRecordParam;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.filecoin.entity.FiledRecords;

public interface FiledRecordsService extends SqlBaseService<FiledRecords,Long> {

    void addFileCoinRecords(AddRecordParam param);
}
