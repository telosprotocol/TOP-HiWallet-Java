package com.topnetwork.wallet.service.impl;

import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.result.wallet.CoinFeeResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wallet.dao.AppCoinFeeDao;
import com.topnetwork.wallet.entity.AppCoinFee;
import com.topnetwork.wallet.service.AppCoinFeeService;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

import java.util.ArrayList;
import java.util.List;

@Service("appCoinFeeService")
@Transactional
public class AppCoinFeeServiceImpl extends SqlBaseServiceImpl<AppCoinFee, Long>
        implements AppCoinFeeService {

    @SuppressWarnings("unused")
    private AppCoinFeeDao appCoinFeeDao;

    public AppCoinFeeServiceImpl(@Qualifier("appCoinFeeDao") AppCoinFeeDao appCoinFeeDao) {
        super(appCoinFeeDao);
        this.appCoinFeeDao = appCoinFeeDao;
    }

    @Override
    public List<CoinFeeResult> queryByChainType(ChainTypeEnum chainType) {

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("chainType", chainType.toString());
        List<AppCoinFee> feeList = appCoinFeeDao.queryForList(wrapper);
        List<CoinFeeResult> feeResults = new ArrayList<>();
        for (AppCoinFee appCoinFee : feeList) {
            CoinFeeResult coinFeeResult = new CoinFeeResult();
            coinFeeResult.setAmount(appCoinFee.getAmount());
            coinFeeResult.setDesc(appCoinFee.getChineseDesc());
            feeResults.add(coinFeeResult);
        }
        return feeResults;
    }
}
