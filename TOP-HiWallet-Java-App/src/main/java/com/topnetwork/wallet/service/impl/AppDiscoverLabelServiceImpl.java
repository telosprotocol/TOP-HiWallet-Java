package com.topnetwork.wallet.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.base.core.mvc.business.CommonBusiness;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.discover.AppType;
import com.topnetwork.wallet.common.enums.discover.LabelType;
import com.topnetwork.wallet.dao.AppDiscoverLabelDao;
import com.topnetwork.wallet.entity.AppDiscoverLabel;
import com.topnetwork.wallet.entity.AppDiscoverLabelI18n;
import com.topnetwork.wallet.param.wallet.discover.AddLabelI18nParam;
import com.topnetwork.wallet.param.wallet.discover.AddLabelParam;
import com.topnetwork.wallet.param.wallet.discover.GetAllLabelParam;
import com.topnetwork.wallet.param.wallet.discover.GetLabelsParam;
import com.topnetwork.wallet.param.wallet.discover.UpdLabelI18nParam;
import com.topnetwork.wallet.param.wallet.discover.UpdateLabelParam;
import com.topnetwork.wallet.result.wallet.discover.GetAllLabelResult;
import com.topnetwork.wallet.result.wallet.discover.GetLabelsResult;
import com.topnetwork.wallet.service.AppDiscoverLabelI18nService;
import com.topnetwork.wallet.service.AppDiscoverLabelService;

import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

@Service("appDiscoverLabelService")
@Transactional
public class AppDiscoverLabelServiceImpl extends SqlBaseServiceImpl<AppDiscoverLabel, Long>
        implements AppDiscoverLabelService {

    @SuppressWarnings("unused")
    private AppDiscoverLabelDao appDiscoverLabelDao;
    @Autowired
    private AppDiscoverLabelI18nService appDiscoverLabelI18nService;

    public AppDiscoverLabelServiceImpl(@Qualifier("appDiscoverLabelDao") AppDiscoverLabelDao appDiscoverLabelDao) {
        super(appDiscoverLabelDao);
        this.appDiscoverLabelDao = appDiscoverLabelDao;
    }

    @Override
    public void addLabel(AddLabelParam param) {
        if (param.getNames() == null || param.getNames().size() <= 0) {
            throw new BusinessException(CodeRes.CODE_20007);
        }
        AppDiscoverLabel appDiscoverLabel = new AppDiscoverLabel();
        appDiscoverLabel.setLabelType(param.getLabelType());
        Long id = appDiscoverLabelDao.getSnowflakeIdWorkerNextId();
        appDiscoverLabel.setId(id);
        appDiscoverLabelDao.persist(appDiscoverLabel);
        List<AppDiscoverLabelI18n> list = new ArrayList<>();
        for (AddLabelI18nParam name : param.getNames()) {
            AppDiscoverLabelI18n appDiscoverLabelI18n = new AppDiscoverLabelI18n();
            appDiscoverLabelI18n.setLabelId(id);
            appDiscoverLabelI18n.setLanguage(name.getLanguage());
            appDiscoverLabelI18n.setName(name.getName());
            appDiscoverLabelI18n.setCreatedDate(new Date());
            appDiscoverLabelI18n.setModifiedDate(new Date());
            list.add(appDiscoverLabelI18n);
        }
        if (list.size() <= 0) {
            throw new BusinessException(CodeRes.CODE_20007);
        }
        appDiscoverLabelI18nService.saveBatch(list);
    }

    @Override
    public void updateLabel(UpdateLabelParam param) {
        if (param.getNames() == null || param.getNames().size() <= 0) {
            throw new BusinessException(CodeRes.CODE_20007);
        }
        AppDiscoverLabel appDiscoverLabel = getById(param.getLabelId());
        if (appDiscoverLabel == null) {
            throw new BusinessException(CodeRes.CODE_20008);
        }
        appDiscoverLabel.setLabelType(param.getLabelType());
        appDiscoverLabel.setModifiedDate(new Date());
        save(appDiscoverLabel);
        List<AppDiscoverLabelI18n> list = new ArrayList<>();
        for (UpdLabelI18nParam name : param.getNames()) {
            AppDiscoverLabelI18n appDiscoverLabelI18n = new AppDiscoverLabelI18n();
            appDiscoverLabelI18n.setLabelId(appDiscoverLabel.getId());
            appDiscoverLabelI18n.setLanguage(name.getLanguage());
            appDiscoverLabelI18n.setName(name.getName());
            appDiscoverLabelI18n.setId(name.getI18nId());
            appDiscoverLabelI18n.setModifiedDate(new Date());
            appDiscoverLabelI18n.setCreatedDate(new Date());
            list.add(appDiscoverLabelI18n);
        }
        if (list.size() <= 0) {
            throw new BusinessException(CodeRes.CODE_20007);
        }
        appDiscoverLabelI18nService.saveBatch(list);
    }

    private AppDiscoverLabel getById(Long labelId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", labelId);
        return get(appDiscoverLabelDao.queryForList(wrapper));
    }

    @Override
    public QueryResult<List<GetLabelsResult>> getLabels(GetLabelsParam param) {
        QueryResult<List<GetLabelsResult>> listQueryResult = new QueryResult<>();
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        QueryResult<List<AppDiscoverLabel>> queryResultSource = queryForPage(AppDiscoverLabel.class, "findLabelPage", params);
        if(ObjectUtils.isEmpty(queryResultSource)) {
        	return listQueryResult;
        }
        listQueryResult.setPageInfo(queryResultSource.getPageInfo());
        List<GetLabelsResult> list = new ArrayList<>();
        for (AppDiscoverLabel appDiscoverGroup : queryResultSource.getResult()) {
            GetLabelsResult getGroupsResult = new GetLabelsResult();
            getGroupsResult.setLabelId(appDiscoverGroup.getId());
            getGroupsResult.setLabelType(appDiscoverGroup.getLabelType());
            getGroupsResult.setNames(appDiscoverLabelI18nService.getByLabelId(appDiscoverGroup.getId()));
            list.add(getGroupsResult);
        }
        listQueryResult.setResult(list);
        return listQueryResult;
    }

    @Override
    public List<GetAllLabelResult> getAllLabel(GetAllLabelParam param) {
        String sql = "SELECT la.id as labelId,lai.`name` as name " +
                "FROM wal_app_discover_label la,wal_app_discover_label_i18n lai " +
                "WHERE la.labelType='" + param.getLabelType().toString() + "' " +
                "AND lai.`language`='CN' " +
                "AND la.id=lai.labelId";
        List<GetAllLabelResult> GetAllLabelResult = result(GetAllLabelResult.class,appDiscoverLabelDao.queryForMap(sql));
//        List<GetAllLabelResult> GetAllLabelResult = queryForMap(GetAllLabelResult.class, sql, null);
        return GetAllLabelResult;
    }

    @Override
    public List<GetAllLabelResult> getByAppIdAndType(Long appId, AppType dapp) {
        LabelType labelType = dapp.equals(AppType.APP) ? LabelType.APPLICATION : LabelType.DAPP;
        String sql = "SELECT la.id as labelId,lai.`name` as name " +
                "FROM wal_app_discover_label la,wal_app_discover_label_i18n lai,wal_app_discover_label_application lapp " +
                "WHERE la.labelType='" + labelType.toString() + "' " +
                "AND lai.`language`='CN' " +
                "AND la.id=lai.labelId " +
                "AND lapp.appId=" + appId + " " +
                "AND lapp.appType='" + dapp.toString() + "' " +
                "AND lapp.labelId = la.id";

        List<GetAllLabelResult> GetAllLabelResult = result(GetAllLabelResult.class,appDiscoverLabelDao.queryForMap(sql));
//        List<GetAllLabelResult> GetAllLabelResult = queryForMap(GetAllLabelResult.class, sql, null);
        return GetAllLabelResult;
    }

}
