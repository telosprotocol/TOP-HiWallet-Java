package com.topnetwork.wallet.service.impl;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.discover.AppType;
import com.topnetwork.wallet.entity.AppDiscoverLabelI18n;
import com.topnetwork.wallet.param.wallet.discover.AddDappLabelParam;
import com.topnetwork.wallet.service.AppDiscoverLabelI18nService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.gitee.magic.framework.head.exception.BusinessException;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wallet.dao.AppDiscoverLabelApplicationDao;
import com.topnetwork.wallet.entity.AppDiscoverLabelApplication;
import com.topnetwork.wallet.service.AppDiscoverLabelApplicationService;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.update.DeleteWrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("appDiscoverLabelApplicationService")
@Transactional
public class AppDiscoverLabelApplicationServiceImpl extends SqlBaseServiceImpl<AppDiscoverLabelApplication, Long>
        implements AppDiscoverLabelApplicationService {

    @SuppressWarnings("unused")
    private AppDiscoverLabelApplicationDao appDiscoverLabelApplicationDao;

    public AppDiscoverLabelApplicationServiceImpl(@Qualifier("appDiscoverLabelApplicationDao") AppDiscoverLabelApplicationDao appDiscoverLabelApplicationDao) {
        super(appDiscoverLabelApplicationDao);
        this.appDiscoverLabelApplicationDao = appDiscoverLabelApplicationDao;
    }

    @Autowired
    private AppDiscoverLabelI18nService appDiscoverLabelI18nService;

    @Override
    public String[] getByTypeAndAppId(AppType dapp, Long targetId, LanguageEnum language) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("appId", targetId).eq("appType", dapp.toString());
        List<AppDiscoverLabelApplication> list = appDiscoverLabelApplicationDao.queryForList(wrapper);
        if (list == null || list.size() == 0) {
            return null;
        }
        String[] tips = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            AppDiscoverLabelApplication appDiscoverLabelApplication = list.get(i);
            AppDiscoverLabelI18n appDiscoverLabelI18n = appDiscoverLabelI18nService.findByLabelIdAndLanguage(appDiscoverLabelApplication.getLabelId(), language);
            if (appDiscoverLabelI18n != null) {
                tips[i] = appDiscoverLabelI18n.getName();
            }
        }

        return tips;
    }

    private List<AppDiscoverLabelApplication> getByTypeAndAppId(AppType dapp, Long appId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("appId", appId).eq("appType", dapp.toString());
        List<AppDiscoverLabelApplication> list = appDiscoverLabelApplicationDao.queryForList(wrapper);
        return list;
    }

    @Override
    public void saveLabel(Long appId, List<AddDappLabelParam> labels, AppType dapp) {
        if (labels == null || labels.size() <= 0) {
            throw new BusinessException(CodeRes.CODE_20019);
        }
        List<AppDiscoverLabelApplication> list = new ArrayList<>();
        DeleteWrapper wrapper = new DeleteWrapper();
        wrapper.eq("appId", appId).eq("appType", dapp.toString());
        appDiscoverLabelApplicationDao.executeUpdate(wrapper);
        for (AddDappLabelParam label : labels) {
            AppDiscoverLabelApplication appDiscoverLabelApplication = new AppDiscoverLabelApplication();
            appDiscoverLabelApplication.setAppId(appId);
            appDiscoverLabelApplication.setAppType(dapp);
            appDiscoverLabelApplication.setCreatedDate(new Date());
            appDiscoverLabelApplication.setModifiedDate(new Date());
            appDiscoverLabelApplication.setLabelId(label.getLabelId());
            list.add(appDiscoverLabelApplication);
        }
        saveBatch(list);
    }
}
