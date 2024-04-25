package com.topnetwork.wallet.service.impl;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.result.wallet.discover.GetLabelsI18nResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.topnetwork.wallet.dao.AppDiscoverLabelI18nDao;
import com.topnetwork.wallet.entity.AppDiscoverLabelI18n;
import com.topnetwork.wallet.service.AppDiscoverLabelI18nService;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("appDiscoverLabelI18nService")
public class AppDiscoverLabelI18nServiceImpl extends SqlBaseServiceImpl<AppDiscoverLabelI18n, Long>
        implements AppDiscoverLabelI18nService {

    @SuppressWarnings("unused")
    private AppDiscoverLabelI18nDao appDiscoverLabelI18nDao;

    public AppDiscoverLabelI18nServiceImpl(@Qualifier("appDiscoverLabelI18nDao") AppDiscoverLabelI18nDao appDiscoverLabelI18nDao) {
        super(appDiscoverLabelI18nDao);
        this.appDiscoverLabelI18nDao = appDiscoverLabelI18nDao;
    }

    @Override
    public List<GetLabelsI18nResult> getByLabelId(Long labelId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("labelId", labelId);
        List<AppDiscoverLabelI18n> appDiscoverLabelI18ns = appDiscoverLabelI18nDao.queryForList(wrapper);
        List<GetLabelsI18nResult> results = new ArrayList<>();
        for (AppDiscoverLabelI18n appDiscoverLabelI18n : appDiscoverLabelI18ns) {
            GetLabelsI18nResult getLabelsI18nResult = new GetLabelsI18nResult();
            BeanUtils.copyProperties(appDiscoverLabelI18n, getLabelsI18nResult);
            getLabelsI18nResult.setI18nId(appDiscoverLabelI18n.getId());
            results.add(getLabelsI18nResult);
        }
        return results;
    }

    @Override
    public AppDiscoverLabelI18n findByLabelIdAndLanguage(Long labelId, LanguageEnum language) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("labelId", labelId).eq("language", language.toString());
        return get(appDiscoverLabelI18nDao.queryForList(wrapper));
    }
}
