package com.topnetwork.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.base.core.mvc.business.CommonBusiness;
import com.base.service.base.system.dao.ConfigureDao;
import com.base.service.base.system.entity.ConfigureDO;
import com.base.service.base.system.service.impl.ConfigureServiceImpl;
import com.base.core.head.enums.ValueTypeEnum;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.param.wallet.appuser.ConfigAddParam;
import com.topnetwork.wallet.param.wallet.appuser.ConfigPageParam;
import com.topnetwork.wallet.param.wallet.appuser.ConfigUpdParam;
import com.topnetwork.wallet.result.wallet.appuser.ConfigPageResult;

import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.framework.base.result.QueryResult;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;
import com.gitee.magic.core.json.JsonArray;
import com.gitee.magic.core.json.JsonObject;

@Service
public class ConfigManagerService extends ConfigureServiceImpl {

    private ConfigureDao configureDao;

    public ConfigManagerService(@Qualifier("configureDao") ConfigureDao configureDao) {
        super(configureDao);
        this.configureDao = configureDao;
    }

    public QueryResult<List<ConfigPageResult>> findConfigPage(ConfigPageParam param) {
        QueryResult<List<ConfigPageResult>> queryResult = new QueryResult<>();
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        QueryResult<List<ConfigureDO>> queryResultSource = queryForPage(ConfigureDO.class, "findConfigurePage", params);
        if(ObjectUtils.isEmpty(queryResultSource)) {
        	return queryResult;
        }
        List<ConfigureDO> list = queryResultSource.getResult();
        List<ConfigPageResult> results = new ArrayList<>();
        for (ConfigureDO configure : list) {
            ConfigPageResult configPageResult = new ConfigPageResult();
            configPageResult.setId(configure.getId());
            configPageResult.setName(configure.getRemark());
            configPageResult.setValue(configure.getValue());
            configPageResult.setType(configure.getType());
            configPageResult.setCode(configure.getCode());
            results.add(configPageResult);
        }
        queryResult.setResult(results);
        queryResult.setPageInfo(queryResultSource.getPageInfo());
        return queryResult;
    }

    public void updateConfig(ConfigUpdParam param) {

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", param.getId());
        ConfigureDO configure = get(configureDao.queryForList(wrapper));
        if (configure == null) {
            throw new BusinessException(CodeRes.CODE_15063);
        }
        if (configure.getType().equals(ValueTypeEnum.JSONArray)) {
            JsonArray jsonArray = new JsonArray(param.getValue());
            configure.setValue(jsonArray.toString());
        }
        if (configure.getType().equals(ValueTypeEnum.JSONObject)) {
            JsonObject jsonObject = new JsonObject(param.getValue());
            configure.setValue(jsonObject.toString());
        }
        if (configure.getType().equals(ValueTypeEnum.String)) {
            configure.setValue(param.getValue());
        }
        configure.setRemark(param.getName());

        save(configure);
    }

    public void addConfig(ConfigAddParam param) {
        ConfigureDO configure = new ConfigureDO();
        configure.setRemark(param.getName());
        configure.setCode(param.getCode());
        configure.setType(param.getType());
        if (param.getType().equals(ValueTypeEnum.JSONArray)) {
            JsonArray jsonArray = new JsonArray(param.getValue());
            configure.setValue(jsonArray.toString());
        }
        if (param.getType().equals(ValueTypeEnum.JSONObject)) {
            JsonObject jsonObject = new JsonObject(param.getValue());
            configure.setValue(jsonObject.toString());
        }
        if (param.getType().equals(ValueTypeEnum.String)) {
            configure.setValue(param.getValue());
        }
        configure.setDisable(false);
        save(configure);
    }
}
