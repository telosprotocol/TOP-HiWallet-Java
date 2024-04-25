package com.topnetwork.filecoin.service.impl;

import com.common.utils.DateUtils;
import com.topnetwork.wallet.param.filecoin.AddRecordParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.filecoin.dao.FiledRecordsDao;
import com.topnetwork.filecoin.entity.FiledRecords;
import com.topnetwork.filecoin.service.FiledRecordsService;

import java.util.Date;

@Service("filedRecordsService")
public class FiledRecordsServiceImpl extends SqlBaseServiceImpl<FiledRecords, Long>
        implements FiledRecordsService {

    @SuppressWarnings("unused")
    private FiledRecordsDao filedRecordsDao;

    public FiledRecordsServiceImpl(@Qualifier("filedRecordsDao") FiledRecordsDao filedRecordsDao) {
        super(filedRecordsDao);
        this.filedRecordsDao = filedRecordsDao;
    }

    @Override
    public void addFileCoinRecords(AddRecordParam param) {
        FiledRecords filedRecords = new FiledRecords();
        filedRecords.setAmount(param.getAmount());
        filedRecords.setEmail(param.getEmail());
        filedRecords.setEndTime(DateUtils.getDayEnd(new Date(param.getEndTime())));
        filedRecords.setMobile(param.getMobile());
        filedRecords.setPayAddress(param.getPayAddress());
        filedRecords.setReceiveAddress(param.getReceiveAddress());
        filedRecords.setStartTime(DateUtils.getDayStart(new Date(param.getStartTime())));
        filedRecords.setWechat(param.getWechat());
        filedRecords.setStakingAddress(param.getStakingAddress());
        filedRecords.setSource(param.getSource());
        filedRecords.setCreatedDate(new Date());
        filedRecords.setModifiedDate(new Date());
        save(filedRecords);
    }
}
