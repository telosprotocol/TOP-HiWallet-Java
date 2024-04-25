package com.topnetwork.wallet.service;

import com.topnetwork.wallet.param.wallet.activity.GameConfigUpdParam;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppGameConfig;

import java.util.List;

public interface AppGameConfigService extends SqlBaseService<AppGameConfig,Long> {

    AppGameConfig findTicketByAid(Long aid);

    List<AppGameConfig> findByAid(Long aid);

    void updateGameConfig(GameConfigUpdParam param);
}
