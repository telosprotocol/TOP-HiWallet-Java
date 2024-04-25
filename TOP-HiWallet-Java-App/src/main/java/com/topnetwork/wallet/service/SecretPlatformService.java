package com.topnetwork.wallet.service;

import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.SecretPlatform;

public interface SecretPlatformService extends SqlBaseService<SecretPlatform,Long> {

    SecretPlatform findByAccessId(String accessId);
}
