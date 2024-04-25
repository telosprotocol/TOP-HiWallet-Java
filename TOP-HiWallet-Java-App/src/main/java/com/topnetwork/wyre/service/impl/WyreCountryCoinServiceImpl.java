package com.topnetwork.wyre.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wyre.dao.WyreCountryCoinDao;
import com.topnetwork.wyre.entity.WyreCountryCoin;
import com.topnetwork.wyre.service.WyreCountryCoinService;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.update.DeleteWrapper;

@Service("wyreCountryCoinService")
public class WyreCountryCoinServiceImpl extends SqlBaseServiceImpl<WyreCountryCoin, Long>
        implements WyreCountryCoinService {

    @SuppressWarnings("unused")
    private WyreCountryCoinDao wyreCountryCoinDao;

    public WyreCountryCoinServiceImpl(@Qualifier("wyreCountryCoinDao") WyreCountryCoinDao wyreCountryCoinDao) {
        super(wyreCountryCoinDao);
        this.wyreCountryCoinDao = wyreCountryCoinDao;
    }

    @Override
    public void deleteByCountryId(Long countryId) {
        DeleteWrapper wrapper = new DeleteWrapper();
        wrapper.eq("countryId", countryId);
        wyreCountryCoinDao.executeUpdate(wrapper);
    }
}
