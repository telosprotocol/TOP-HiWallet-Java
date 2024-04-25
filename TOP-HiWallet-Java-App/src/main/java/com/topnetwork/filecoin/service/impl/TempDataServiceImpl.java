package com.topnetwork.filecoin.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.filecoin.dao.TempDataDao;
import com.topnetwork.filecoin.entity.TempData;
import com.topnetwork.filecoin.service.TempDataService;

import java.util.Date;

@Service("tempDataService")
public class TempDataServiceImpl extends SqlBaseServiceImpl<TempData, Long>
        implements TempDataService {

    @SuppressWarnings("unused")
    private TempDataDao tempDataDao;

    public TempDataServiceImpl(@Qualifier("tempDataDao") TempDataDao tempDataDao) {
        super(tempDataDao);
        this.tempDataDao = tempDataDao;
    }

    @Override
    public void saveCid(String cid, String type, String size, String fileName) {
        TempData tempData = new TempData();
        tempData.setCid(cid);
        tempData.setType(type);
        tempData.setFileName(fileName);
        tempData.setSize(size);
        tempData.setCreatedDate(new Date());
        tempData.setModifiedDate(new Date());
        save(tempData);
    }
}
