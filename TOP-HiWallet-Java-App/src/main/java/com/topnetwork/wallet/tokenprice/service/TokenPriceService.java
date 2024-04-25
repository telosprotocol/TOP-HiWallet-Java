package com.topnetwork.wallet.tokenprice.service;

import com.topnetwork.wallet.tokenprice.entity.TokenPrice;
import com.base.core.framework.sql.service.SqlBaseService;

import java.util.List;

public interface TokenPriceService extends SqlBaseService<TokenPrice, Long> {

    String getAllIds();

    List<TokenPrice> findByCoinId(Long coinId);

    List<TokenPrice> findByEnglishName(String replaceAll);
}
