package com.topnetwork.wallet.service;

import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppInviteCode;

public interface AppInviteCodeService extends SqlBaseService<AppInviteCode,Long> {

    String getByUid(String uid);

    AppInviteCode getByInviteCode(String inviteCode);
}
