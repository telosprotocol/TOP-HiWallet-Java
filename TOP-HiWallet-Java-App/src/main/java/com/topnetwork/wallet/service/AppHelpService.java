package com.topnetwork.wallet.service;

import com.topnetwork.system.entity.User;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppHelp;
import com.topnetwork.wallet.param.wallet.*;
import com.topnetwork.wallet.result.wallet.*;

import java.util.List;

public interface AppHelpService extends SqlBaseService<AppHelp, Long> {

    QueryResult<List<HelpListResult>> findHelpList(HelpListParam param);

    HelpDetailResult findHelpDetail(HelpDetailParam param);

    void updateHelp(HelpUpdParam helpUpdParam, User user);

    void addHelp(HelpAddParam helpAddParam, User user);

    void delHelp(HelpDelParam param);

    List<HelpWalletFristResult> findFistHelpForWallet(HelpWalletParam param);

    HelpWalletSecResult findSecHelpForWallet(HelpWalletParam param);

    QueryResult<List<HelpSearchResult>> search(HelpSearchParam param);
}
