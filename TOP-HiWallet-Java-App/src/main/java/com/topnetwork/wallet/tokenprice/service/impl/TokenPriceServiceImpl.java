package com.topnetwork.wallet.tokenprice.service.impl;

import com.topnetwork.wallet.tokenprice.dao.TokenPriceDao;
import com.topnetwork.wallet.tokenprice.entity.TokenPrice;
import com.topnetwork.wallet.tokenprice.service.TokenPriceService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.util.ObjectUtils;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

import java.util.List;
import java.util.stream.Collectors;

@Service("tokenPriceService")
public class TokenPriceServiceImpl extends SqlBaseServiceImpl<TokenPrice, Long>
        implements TokenPriceService {

    @SuppressWarnings("unused")
    private TokenPriceDao tokenPriceDao;

    public TokenPriceServiceImpl(@Qualifier("tokenPriceDao") TokenPriceDao tokenPriceDao) {
        super(tokenPriceDao);
        this.tokenPriceDao = tokenPriceDao;
    }

    @Override
    public String getAllIds() {
        List<TokenPrice> all = queryForAll();
        String collect = all.stream().filter(tokenPrice -> !ObjectUtils.isEmpty(tokenPrice.getIds())).map(tokenPrice -> tokenPrice.getIds()
        ).collect(Collectors.joining(","));
        return collect;
    }

    @Override
    public List<TokenPrice> findByCoinId(Long coinId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("coinId", coinId);
        return tokenPriceDao.queryForList(wrapper);
    }

    @Override
    public List<TokenPrice> findByEnglishName(String englishName) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("englishName", englishName);
        return tokenPriceDao.queryForList(wrapper);
    }
}
