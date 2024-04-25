package com.topnetwork.filecoin.service;

import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.filecoin.entity.TempData;

public interface TempDataService extends SqlBaseService<TempData,Long> {

    void saveCid(String cid,String type,String size,String fileName);
}
