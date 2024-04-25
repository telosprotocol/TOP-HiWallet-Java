package com.topnetwork.wallet.service.impl;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.discover.AppType;
import com.topnetwork.wallet.param.wallet.discover.GroupI18nParam;
import com.topnetwork.wallet.result.wallet.discover.ProI18nGroupResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.gitee.magic.framework.head.exception.BusinessException;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wallet.dao.AppDiscoverGroupI18nDao;
import com.topnetwork.wallet.entity.AppDiscoverGroupI18n;
import com.topnetwork.wallet.service.AppDiscoverGroupI18nService;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.update.DeleteWrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("appDiscoverGroupI18nService")
@Transactional
public class AppDiscoverGroupI18nServiceImpl extends SqlBaseServiceImpl<AppDiscoverGroupI18n, Long>
        implements AppDiscoverGroupI18nService {

    @SuppressWarnings("unused")
    private AppDiscoverGroupI18nDao appDiscoverGroupI18nDao;

    public AppDiscoverGroupI18nServiceImpl(@Qualifier("appDiscoverGroupI18nDao") AppDiscoverGroupI18nDao appDiscoverGroupI18nDao) {
        super(appDiscoverGroupI18nDao);
        this.appDiscoverGroupI18nDao = appDiscoverGroupI18nDao;
    }

    @Override
    public List<AppDiscoverGroupI18n> findByGroupId(Long groupId, Boolean showToHome) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("groupId", groupId);
        if (showToHome != null) {
            wrapper.eq("showToHome", showToHome.toString());
        }
        wrapper.orderByAsc("appOrder");
        return appDiscoverGroupI18nDao.queryForList(wrapper);
    }

    @Override
    public List<ProI18nGroupResult> findByI18nIdAndType(Long i18nId, AppType pro) {
        List<AppDiscoverGroupI18n> list = findByI18nIdAndTypeBase(i18nId, pro);
        List<ProI18nGroupResult> results = new ArrayList<>();
        for (AppDiscoverGroupI18n appDiscoverGroupI18n : list) {
            ProI18nGroupResult proI18nGroupResult = new ProI18nGroupResult();
            proI18nGroupResult.setGroupId(appDiscoverGroupI18n.getGroupId());
            proI18nGroupResult.setShowOrder(appDiscoverGroupI18n.getAppOrder());
            proI18nGroupResult.setShowToHome(appDiscoverGroupI18n.getShowToHome());
            results.add(proI18nGroupResult);
        }
        return results;
    }

    private List<AppDiscoverGroupI18n> findByI18nIdAndTypeBase(Long i18nId, AppType pro) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("i18nId", i18nId).eq("appType", pro.toString());
        return appDiscoverGroupI18nDao.queryForList(wrapper);
    }

    @Override
    public void save(Long i18nId, List<GroupI18nParam> groups, AppType appType) {
        if (groups == null || groups.size() <= 0) {
            throw new BusinessException(CodeRes.CODE_20023);
        }
        DeleteWrapper wrapper = new DeleteWrapper();
        wrapper.eq("i18nId", i18nId).eq("appType", appType.toString());
        appDiscoverGroupI18nDao.executeUpdate(wrapper);
        List<AppDiscoverGroupI18n> list = new ArrayList<>();
        List<AppDiscoverGroupI18n> proI18nGroupResults = findByI18nIdAndTypeBase(i18nId, appType);
        for (GroupI18nParam group : groups) {
            boolean hasExit = false;
            Long appDiscoverGroupI18nId = null;
            for (AppDiscoverGroupI18n appDiscoverGroupI18n : proI18nGroupResults) {
                if (appDiscoverGroupI18n.getGroupId().equals(group.getGroupId())) {
                    hasExit = true;
                    appDiscoverGroupI18nId = appDiscoverGroupI18n.getId();
                    break;
                }
            }

            AppDiscoverGroupI18n appDiscoverGroupI18n = new AppDiscoverGroupI18n();
            appDiscoverGroupI18n.setAppOrder(group.getShowOrder());
            appDiscoverGroupI18n.setAppType(appType);
            appDiscoverGroupI18n.setGroupId(group.getGroupId());
            appDiscoverGroupI18n.setI18nId(i18nId);
            appDiscoverGroupI18n.setShowToHome(group.getShowToHome());
            appDiscoverGroupI18n.setCreatedDate(new Date());

            appDiscoverGroupI18n.setModifiedDate(new Date());
            if (hasExit) {
                appDiscoverGroupI18n.setId(appDiscoverGroupI18nId);
            }
            list.add(appDiscoverGroupI18n);
        }
        saveBatch(list);
    }
}
