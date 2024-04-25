package com.topnetwork.wallet.tokenprice.dao.impl;

import com.topnetwork.wallet.tokenprice.dao.TokenPriceDao;
import com.topnetwork.wallet.tokenprice.entity.TokenPrice;
import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;


@Repository("tokenPriceDao")
public class TokenPriceDaoImpl extends SqlBaseDaoImpl<TokenPrice, Long> implements TokenPriceDao {

    public TokenPriceDaoImpl() {
        super(TokenPrice.class);
    }

}
