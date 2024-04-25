package com.topnetwork.wallet.service.impl;

import com.base.core.mvc.business.CommonBusiness;
import com.common.utils.DateUtils;
import com.topnetwork.wallet.param.wallet.activity.BtcPriceListParam;
import com.topnetwork.wallet.result.wallet.activity.BtcPriceListResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wallet.dao.AppBtcPriceDao;
import com.topnetwork.wallet.entity.AppBtcPrice;
import com.topnetwork.wallet.service.AppBtcPriceService;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("appBtcPriceService")
@Transactional
public class AppBtcPriceServiceImpl extends SqlBaseServiceImpl<AppBtcPrice, Long>
        implements AppBtcPriceService {

    @SuppressWarnings("unused")
    private AppBtcPriceDao appBtcPriceDao;

    public AppBtcPriceServiceImpl(@Qualifier("appBtcPriceDao") AppBtcPriceDao appBtcPriceDao) {
        super(appBtcPriceDao);
        this.appBtcPriceDao = appBtcPriceDao;
    }

    @Override
    public AppBtcPrice getByPhase(String phase) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("phase", phase);
        return get(appBtcPriceDao.queryForList(wrapper));
    }

    @Override
    public QueryResult<List<BtcPriceListResult>> findInWeekList(BtcPriceListParam param) {

        List<BtcPriceListResult> results = new ArrayList<>();
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        params.put("createTime", DateUtils.getWeekBeforeDate(new Date()));
        QueryResult<List<AppBtcPrice>> queryResult = queryForPage(AppBtcPrice.class, "getBtcPriceList", params);
        QueryResult<List<BtcPriceListResult>> listQueryResult = new QueryResult<>();
        if(ObjectUtils.isEmpty(queryResult)) {
        	return listQueryResult;
        }
        List<AppBtcPrice> list = queryResult.getResult();
        for (AppBtcPrice appBtcPrice : list) {
            BtcPriceListResult btcPriceListResult = new BtcPriceListResult();
            BeanUtils.copyProperties(appBtcPrice, btcPriceListResult);
            results.add(btcPriceListResult);
        }
        listQueryResult.setPageInfo(queryResult.getPageInfo());
        listQueryResult.setResult(results);
        return listQueryResult;
    }
}
