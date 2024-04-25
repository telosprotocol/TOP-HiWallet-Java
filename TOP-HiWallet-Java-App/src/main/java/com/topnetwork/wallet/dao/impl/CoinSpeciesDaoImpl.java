package com.topnetwork.wallet.dao.impl;

import org.springframework.stereotype.Repository;
import com.base.core.framework.sql.dao.impl.SqlBaseDaoImpl;

import com.topnetwork.wallet.dao.CoinSpeciesDao;
import com.topnetwork.wallet.entity.CoinSpecies;

@Repository("coinSpeciesDao")
public class CoinSpeciesDaoImpl extends SqlBaseDaoImpl<CoinSpecies, Long> implements CoinSpeciesDao {

    public CoinSpeciesDaoImpl() {
        super(CoinSpecies.class);
    }

    @Override
    public boolean saveWithId(CoinSpecies coinSpecies) {
        long i = persist(coinSpecies);
        return i > 0;
    }
}
