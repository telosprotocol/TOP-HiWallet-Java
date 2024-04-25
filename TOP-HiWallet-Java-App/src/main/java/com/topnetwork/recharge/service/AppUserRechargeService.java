package com.topnetwork.recharge.service;

import com.topnetwork.system.entity.User;
import com.topnetwork.wallet.param.recharge.GetRechargeHistoryParam;
import com.topnetwork.wallet.param.recharge.RechargeListParam;
import com.topnetwork.wallet.result.recharge.GetRechargeHistoryResult;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.recharge.entity.AppUserRecharge;

import java.util.List;

public interface AppUserRechargeService extends SqlBaseService<AppUserRecharge, Long> {

    QueryResult<List<GetRechargeHistoryResult>> getRechargeHistory(GetRechargeHistoryParam param);

    void recharge(RechargeListParam param, User user);

    void getPhoneCode();
}
