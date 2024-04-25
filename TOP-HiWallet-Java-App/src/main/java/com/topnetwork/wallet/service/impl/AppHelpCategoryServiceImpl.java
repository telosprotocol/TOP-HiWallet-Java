package com.topnetwork.wallet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topnetwork.wallet.dao.AppHelpCategoryDao;
import com.topnetwork.wallet.entity.AppHelpCategory;
import com.topnetwork.wallet.param.wallet.CategoryListParam;
import com.topnetwork.wallet.result.wallet.CategoryListResult;
import com.topnetwork.wallet.service.AppHelpCategoryService;

import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

@Service("appHelpCategoryService")
@Transactional
public class AppHelpCategoryServiceImpl extends SqlBaseServiceImpl<AppHelpCategory, Long>
        implements AppHelpCategoryService {

    private AppHelpCategoryDao appHelpCategoryDao;

    public AppHelpCategoryServiceImpl(@Qualifier("appHelpCategoryDao") AppHelpCategoryDao appHelpCategoryDao) {
        super(appHelpCategoryDao);
        this.appHelpCategoryDao = appHelpCategoryDao;
    }

    @Override
    public List<CategoryListResult> findCategoryList(CategoryListParam param) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (param.getLevel() != null) {
            queryWrapper.eq("level", param.getLevel());
        }
        if (param.getFid() != null) {
            queryWrapper.eq("fatherId", param.getLevel());
        }
        List<AppHelpCategory> list = findByWrapper(queryWrapper);
        List<CategoryListResult> listResults = new ArrayList<>();
        for (AppHelpCategory appHelpCategory : list) {
            CategoryListResult categoryListResult = new CategoryListResult();
            categoryListResult.setFatherId(appHelpCategory.getFatherId());
            categoryListResult.setId(appHelpCategory.getId());
            categoryListResult.setLevel(appHelpCategory.getLevel());
            categoryListResult.setEnglishName(appHelpCategory.getEnglishName());
            categoryListResult.setName(appHelpCategory.getName());
            listResults.add(categoryListResult);
        }
        return listResults;
    }

    @Override
    public List<AppHelpCategory> findByWrapper(QueryWrapper wrapper) {
        return appHelpCategoryDao.queryForList(wrapper);
    }

    @Override
    public AppHelpCategory findById(Long stCategoryId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", stCategoryId);
        return get(appHelpCategoryDao.queryForList(queryWrapper));
    }
}
