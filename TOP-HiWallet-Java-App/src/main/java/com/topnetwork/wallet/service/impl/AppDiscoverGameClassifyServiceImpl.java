package com.topnetwork.wallet.service.impl;

import com.topnetwork.wallet.common.enums.discover.DiscoverDataFrom;
import com.topnetwork.wallet.result.wallet.discover.GameClassifyResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wallet.dao.AppDiscoverGameClassifyDao;
import com.topnetwork.wallet.entity.AppDiscoverGameClassify;
import com.topnetwork.wallet.service.AppDiscoverGameClassifyService;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

import java.util.ArrayList;
import java.util.List;

@Service("appDiscoverGameClassifyService")
@Transactional
public class AppDiscoverGameClassifyServiceImpl extends SqlBaseServiceImpl<AppDiscoverGameClassify, Long>
        implements AppDiscoverGameClassifyService {

    @SuppressWarnings("unused")
    private AppDiscoverGameClassifyDao appDiscoverGameClassifyDao;

    public AppDiscoverGameClassifyServiceImpl(@Qualifier("appDiscoverGameClassifyDao") AppDiscoverGameClassifyDao appDiscoverGameClassifyDao) {
        super(appDiscoverGameClassifyDao);
        this.appDiscoverGameClassifyDao = appDiscoverGameClassifyDao;
    }

    @Override
    public AppDiscoverGameClassify findById(Long classifyId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", classifyId);
        return get(appDiscoverGameClassifyDao.queryForList(wrapper));
    }

    @Override
    public List<GameClassifyResult> findGameClassifyList() {
        List<AppDiscoverGameClassify> list = queryForAll();
        List<GameClassifyResult> results = new ArrayList<>();
        for (AppDiscoverGameClassify appDiscoverGameClassify : list) {
            if (appDiscoverGameClassify.getDataFrom().equals(DiscoverDataFrom.mysql)) {
                GameClassifyResult gameClassifyResult = new GameClassifyResult();
                gameClassifyResult.setId(appDiscoverGameClassify.getId());
                gameClassifyResult.setTitle(appDiscoverGameClassify.getTitle());
                gameClassifyResult.setEnglishTitle(appDiscoverGameClassify.getEnglishTitle());
                results.add(gameClassifyResult);
            }
        }
        return results;
    }

    @Override
    public List<AppDiscoverGameClassify> findAllByApiVersion(int apiVersion) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.ge("maxVersion", apiVersion);
        wrapper.le("minVersion", apiVersion);
        return appDiscoverGameClassifyDao.queryForList(wrapper);
    }
}
