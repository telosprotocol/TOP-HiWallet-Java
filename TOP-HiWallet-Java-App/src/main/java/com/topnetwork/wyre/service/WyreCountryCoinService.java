package com.topnetwork.wyre.service;

import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wyre.entity.WyreCountryCoin;

public interface WyreCountryCoinService extends SqlBaseService<WyreCountryCoin,Long> {

    void deleteByCountryId(Long countryId);
}
