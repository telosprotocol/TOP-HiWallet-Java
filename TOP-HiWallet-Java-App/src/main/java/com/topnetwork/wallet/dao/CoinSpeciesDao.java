package com.topnetwork.wallet.dao;

import com.base.core.framework.sql.dao.SqlBaseDao;
import com.topnetwork.wallet.entity.CoinSpecies;

public interface CoinSpeciesDao extends SqlBaseDao<CoinSpecies, Long> {

    boolean saveWithId(CoinSpecies coinSpecies);
}
