package com.topnetwork.wallet.service;

import com.topnetwork.wallet.entity.SecretPlatform;
import com.topnetwork.wallet.param.wallet.appuser.*;
import com.topnetwork.wallet.result.wallet.appuser.*;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppUser;

import java.util.List;

public interface AppUserService extends SqlBaseService<AppUser, Long> {

    UserRegisterResult register(UserRegisterParam param, String requestIP, SecretPlatform secretPlatform);

    UserInfoResult getUserInfo(AppUser appUser);

    AppUser getUserByUid(String uid);

    UserInfoResult getTopBalance(AppUser appUser);

    IsNewResult isNew(IsNewParam param);

    UserCertResult findUserCert(AppUser appUser);

    List<AppUser> findCreateInTimeAll(Integer dayNum);

    AppUser findById(Long userId);

    UserDataResult loginUserData(UserDataParam param);

    UserInfoResult getTopBalanceForGuess(UserInfoParam param);

    UserInfoV2Result getUserV2Info(AppUser appUser);

    void delete();

    AppUser getUserByMobile(Integer countryCode, String mobile);
}
