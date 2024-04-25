package com.topnetwork.wallet.service.impl;

import com.topnetwork.wallet.common.enums.CertType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wallet.dao.AppUserCertDao;
import com.topnetwork.wallet.entity.AppUserCert;
import com.topnetwork.wallet.service.AppUserCertService;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;


@Service("appUserCertService")
@Transactional
public class AppUserCertServiceImpl extends SqlBaseServiceImpl<AppUserCert, Long>
        implements AppUserCertService {

    @SuppressWarnings("unused")
    private AppUserCertDao appUserCertDao;

    public AppUserCertServiceImpl(@Qualifier("appUserCertDao") AppUserCertDao appUserCertDao) {
        super(appUserCertDao);
        this.appUserCertDao = appUserCertDao;
    }


    @Override
    public AppUserCert getByUserId(Long uid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", uid).eq("type", CertType.NEWUSER.toString());
        return get(appUserCertDao.queryForList(queryWrapper));
    }

    @Override
    public AppUserCert getByCertNum(String certNum) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("certNum", certNum).eq("type", CertType.NEWUSER.toString());
        return get(appUserCertDao.queryForList(queryWrapper));
    }

}
