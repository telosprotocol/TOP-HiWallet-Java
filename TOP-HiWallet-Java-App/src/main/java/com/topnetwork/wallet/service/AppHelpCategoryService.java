package com.topnetwork.wallet.service;

import com.base.core.framework.sql.service.SqlBaseService;
import com.topnetwork.wallet.entity.AppHelpCategory;
import com.topnetwork.wallet.param.wallet.CategoryListParam;
import com.topnetwork.wallet.result.wallet.CategoryListResult;

import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

import java.util.List;

public interface AppHelpCategoryService extends SqlBaseService<AppHelpCategory,Long> {

    List<CategoryListResult> findCategoryList(CategoryListParam param);

    List<AppHelpCategory> findByWrapper(QueryWrapper wrapper);

    AppHelpCategory findById(Long stCategoryId);
}
