package com.topnetwork.wallet.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.topnetwork.wallet.common.CodeRes;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.base.core.mvc.business.CommonBusiness;
import com.topnetwork.system.entity.User;
import com.topnetwork.wallet.dao.AppHelpDao;
import com.topnetwork.wallet.entity.AppHelp;
import com.topnetwork.wallet.entity.AppHelpCategory;
import com.topnetwork.wallet.param.wallet.HelpAddParam;
import com.topnetwork.wallet.param.wallet.HelpDelParam;
import com.topnetwork.wallet.param.wallet.HelpDetailParam;
import com.topnetwork.wallet.param.wallet.HelpListParam;
import com.topnetwork.wallet.param.wallet.HelpSearchParam;
import com.topnetwork.wallet.param.wallet.HelpUpdParam;
import com.topnetwork.wallet.param.wallet.HelpWalletParam;
import com.topnetwork.wallet.result.wallet.HelpDetailResult;
import com.topnetwork.wallet.result.wallet.HelpListResult;
import com.topnetwork.wallet.result.wallet.HelpSearchResult;
import com.topnetwork.wallet.result.wallet.HelpWalletFristResult;
import com.topnetwork.wallet.result.wallet.HelpWalletSecResult;
import com.topnetwork.wallet.service.AppHelpCategoryService;
import com.topnetwork.wallet.service.AppHelpService;

import org.springframework.transaction.annotation.Transactional;
import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.update.DeleteWrapper;

@Service("appHelpService")
@Transactional
public class AppHelpServiceImpl extends SqlBaseServiceImpl<AppHelp, Long>
        implements AppHelpService {

    private AppHelpDao appHelpDao;

    @Autowired
    private AppHelpCategoryService appHelpCategoryService;

    public AppHelpServiceImpl(@Qualifier("appHelpDao") AppHelpDao appHelpDao) {
        super(appHelpDao);
        this.appHelpDao = appHelpDao;
    }

    @Override
    public QueryResult<List<HelpListResult>> findHelpList(HelpListParam param) {
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        return queryForPage(HelpListResult.class, "findHelpList", params);
    }

    @Override
    public HelpDetailResult findHelpDetail(HelpDetailParam param) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", param.getId());
        AppHelp appHelp = get(appHelpDao.queryForList(wrapper));
        if (appHelp == null) {
            throw new BusinessException(CodeRes.CODE_14008);
        }
        HelpDetailResult helpDetailResult = new HelpDetailResult();
        BeanUtils.copyProperties(appHelp, helpDetailResult);
        return helpDetailResult;
    }

    @Override
    public void updateHelp(HelpUpdParam helpUpdParam, User user) {
        AppHelp appHelp = new AppHelp();
        appHelp.setId(helpUpdParam.getId());
        appHelp.setContent(helpUpdParam.getContent());
        appHelp.setNdCategoryId(helpUpdParam.getNdCategoryId());
        appHelp.setStCategoryId(helpUpdParam.getStCategoryId());
        appHelp.setTitle(helpUpdParam.getTitle());
        appHelp.setUpdateId(user.getId());
        appHelp.setEnglishTitle(helpUpdParam.getEnglishTitle());
        appHelp.setEnglishContent(helpUpdParam.getEnglishContent());
        appHelp.setUpdateTime(new Date());
        save(appHelp);
    }

    @Override
    public void addHelp(HelpAddParam helpAddParam, User user) {
        AppHelp appHelp = new AppHelp();
        appHelp.setContent(helpAddParam.getContent());
        appHelp.setNdCategoryId(helpAddParam.getNdCategoryId());
        appHelp.setStCategoryId(helpAddParam.getStCategoryId());
        appHelp.setTitle(helpAddParam.getTitle());
        appHelp.setEnglishTitle(helpAddParam.getEnglishTitle());
        appHelp.setEnglishContent(helpAddParam.getEnglishContent());
        appHelp.setCreateId(user.getId());
        appHelp.setCreateTime(new Date());
        save(appHelp);
    }

    @Override
    public void delHelp(HelpDelParam param) {
        DeleteWrapper wrapper = new DeleteWrapper();
        wrapper.eq("id", param.getId());
        appHelpDao.executeUpdate(wrapper);
    }

    @Override
    public List<HelpWalletFristResult> findFistHelpForWallet(HelpWalletParam param) {

        List<HelpWalletFristResult> helpWalletFistResults = new ArrayList<>();

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("fatherId", param.getId());
        List<AppHelpCategory> list = appHelpCategoryService.findByWrapper(wrapper);
        for (AppHelpCategory appHelpCategory : list) {
            HelpWalletFristResult helpWalletFristResult = new HelpWalletFristResult();
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("stCategoryId", param.getId()).eq("ndCategoryId", appHelpCategory.getId());

            List<HelpWalletFristResult.Help> listHelp = new ArrayList<>();
            List<AppHelp> appHelpList = appHelpDao.queryForList(queryWrapper);
            for (AppHelp appHelp : appHelpList) {
                HelpWalletFristResult.Help help = new HelpWalletFristResult.Help();
                help.setId(appHelp.getId());
                help.setTitle(appHelp.getTitle());
                help.setEnglishTitle(appHelp.getEnglishTitle());
                help.setName(appHelp.getStCategoryId());
                help.setType(appHelp.getNdCategoryId());
                listHelp.add(help);
            }
            helpWalletFristResult.setTitle(appHelpCategory.getName());
            helpWalletFristResult.setTitleEn(appHelpCategory.getEnglishName());
            helpWalletFristResult.setList(listHelp);
            helpWalletFistResults.add(helpWalletFristResult);
        }

        return helpWalletFistResults;
    }

    @Override
    public HelpWalletSecResult findSecHelpForWallet(HelpWalletParam param) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", param.getId());
        AppHelp appHelp = get(appHelpDao.queryForList(queryWrapper));
        if (appHelp == null) {
            throw new BusinessException(CodeRes.CODE_14008);
        }
        AppHelpCategory AppHelpCategory_1 = appHelpCategoryService.findById(appHelp.getStCategoryId());
        AppHelpCategory AppHelpCategory_2 = appHelpCategoryService.findById(appHelp.getNdCategoryId());
        HelpWalletSecResult helpWalletSecResult = new HelpWalletSecResult();
        helpWalletSecResult.setTitle_1st(AppHelpCategory_1.getName());
        helpWalletSecResult.setTitle_2nd(AppHelpCategory_2.getName());
        helpWalletSecResult.setTitleEn_1st(AppHelpCategory_1.getEnglishName());
        helpWalletSecResult.setTitleEn_2nd(AppHelpCategory_2.getEnglishName());


        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("stCategoryId", AppHelpCategory_1.getId()).eq("ndCategoryId", AppHelpCategory_2.getId());
        List<AppHelp> appHelpList = appHelpDao.queryForList(wrapper);
        List<HelpWalletFristResult.Help> listHelp = new ArrayList<>();
        for (AppHelp appHelp_for : appHelpList) {
            HelpWalletFristResult.Help help = new HelpWalletFristResult.Help();
            help.setId(appHelp_for.getId());
            help.setTitle(appHelp_for.getTitle());
            help.setEnglishTitle(appHelp_for.getEnglishTitle());
            help.setName(appHelp_for.getStCategoryId());
            help.setType(appHelp_for.getNdCategoryId());
            listHelp.add(help);
        }
        helpWalletSecResult.setList(listHelp);
        return helpWalletSecResult;
    }

    @Override
    public QueryResult<List<HelpSearchResult>> search(HelpSearchParam param) {
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        List<AppHelpCategory> list = appHelpCategoryService.findByWrapper(new QueryWrapper());
        QueryResult<List<HelpSearchResult>> queryResult = queryForPage(HelpSearchResult.class, "search", params);
        List<HelpSearchResult> results = queryResult.getResult();
        for (HelpSearchResult helpSearchResult : results) {
            for (AppHelpCategory appHelpCategory : list) {
                if (helpSearchResult.getStCategoryId().equals(appHelpCategory.getId())) {
                    helpSearchResult.setStname_cn(appHelpCategory.getName());
                    helpSearchResult.setStname_en(appHelpCategory.getEnglishName());
                }
                if (helpSearchResult.getNdCategoryId().equals(appHelpCategory.getId())) {
                    helpSearchResult.setNdname_cn(appHelpCategory.getName());
                    helpSearchResult.setNdname_en(appHelpCategory.getEnglishName());
                }
            }
        }
        return queryResult;
    }
}
