package com.topnetwork.wallet.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wallet.dao.SecretPlatformDao;
import com.topnetwork.wallet.entity.SecretPlatform;
import com.topnetwork.wallet.service.SecretPlatformService;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

@Service("secretPlatformService")
@Transactional
public class SecretPlatformServiceImpl extends SqlBaseServiceImpl<SecretPlatform, Long>
        implements SecretPlatformService {

    @SuppressWarnings("unused")
    private SecretPlatformDao secretPlatformDao;

    public SecretPlatformServiceImpl(@Qualifier("secretPlatformDao") SecretPlatformDao secretPlatformDao) {
        super(secretPlatformDao);
        this.secretPlatformDao = secretPlatformDao;
    }

    @Override
    public SecretPlatform findByAccessId(String accessId) {
        QueryWrapper wrapper = new QueryWrapper().eq("accessId", accessId);
        return get(secretPlatformDao.queryForList(wrapper));
    }
}
