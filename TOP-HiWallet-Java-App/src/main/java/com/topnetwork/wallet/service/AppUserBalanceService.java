package com.topnetwork.wallet.service;

import com.topnetwork.wallet.entity.AppActivityReward;
import com.topnetwork.wallet.entity.AppUser;
import com.topnetwork.wallet.entity.AppWithdrawOrder;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppUserBalance;

import java.math.BigDecimal;
import java.util.List;

public interface AppUserBalanceService extends SqlBaseService<AppUserBalance, Long> {

    void addTopBalance(Long uid, BigDecimal balance);

    AppUserBalance getTopBalance(Long uid);

    void receiveAward(AppActivityReward appActivityReward);

    void unfreezeOnly(List<AppWithdrawOrder> updateList);

    void unfreezeOnly(AppWithdrawOrder appWithdrawOrder);

    void freeze(Long id, BigDecimal amount);

    Long countSameBalance(AppUser appUser);
}
