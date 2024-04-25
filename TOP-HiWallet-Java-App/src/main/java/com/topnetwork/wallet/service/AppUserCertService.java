package com.topnetwork.wallet.service;

import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppUserCert;

public interface AppUserCertService extends SqlBaseService<AppUserCert, Long> {

    AppUserCert getByUserId(Long uid);

    AppUserCert getByCertNum(String certNum);
}
