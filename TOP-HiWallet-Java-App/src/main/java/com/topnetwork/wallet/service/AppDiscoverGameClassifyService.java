package com.topnetwork.wallet.service;

import com.topnetwork.wallet.result.wallet.discover.GameClassifyResult;
import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppDiscoverGameClassify;

import java.util.List;

public interface AppDiscoverGameClassifyService extends SqlBaseService<AppDiscoverGameClassify, Long> {

    AppDiscoverGameClassify findById(Long classifyId);

    List<GameClassifyResult> findGameClassifyList();

    List<AppDiscoverGameClassify> findAllByApiVersion(int apiVersion);
}
