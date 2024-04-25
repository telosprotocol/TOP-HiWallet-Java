package com.topnetwork.wallet.service;

import com.topnetwork.wallet.param.wallet.invite.*;
import com.topnetwork.wallet.result.wallet.invite.*;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppInvite;

import java.util.List;

public interface AppInviteService extends SqlBaseService<AppInvite, Long> {

    UserInfoResult getInfo(UserInfoParam param);

    QueryResult<List<UserListResult>> getList(UserListParam param);

    InviteCodeResult getInviteCode(InviteCodeParam param);

    PhoneCodeResult getPhoneCode(PhoneCodeParam param);

    void recInvite(RecInviteParam param);

    RewardNumberResult getRewardNumber();

    BindPhoneResult appBindPhone(BindPhoneParam param);

    void putCode(PutCodeParam param);

    PhoneCodeResult getPhoneCodeBind(PhoneCodeParam param);

    ManagerPhoneCodeResult getPhoneCodeForManager(ManagerPhoneCodeParam param);

    void verifyPhoneCode(PhoneVerifyParam param);

    void verifyPhoneCodeBind(PhoneVerifyParam param);

    List<Integer> getCountryCodes();
}
