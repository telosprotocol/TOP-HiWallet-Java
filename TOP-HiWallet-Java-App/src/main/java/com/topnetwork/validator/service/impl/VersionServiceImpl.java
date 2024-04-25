package com.topnetwork.validator.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.framework.base.result.QueryResult;
import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.update.DeleteWrapper;
import com.topnetwork.validator.dao.VersionDao;
import com.topnetwork.validator.entity.Version;
import com.topnetwork.validator.service.VersionService;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.PlatformEnum;
import com.topnetwork.wallet.param.wallet.ValidatorUpdateParam;
import com.topnetwork.wallet.param.wallet.VersionDelParam;
import com.topnetwork.wallet.param.wallet.VersionListParam;
import com.topnetwork.wallet.param.wallet.validator.ValidatorAddParam;
import com.topnetwork.wallet.param.wallet.validator.ValidatorStatusParam;
import com.topnetwork.wallet.result.wallet.validator.TableBarConfigResult;
import com.topnetwork.wallet.result.wallet.validator.ValidatorListResult;

@Service("versionService")
public class VersionServiceImpl extends SqlBaseServiceImpl<Version, Long>
        implements VersionService {

    @SuppressWarnings("unused")
    private VersionDao versionDao;

    public VersionServiceImpl(@Qualifier("versionDao") VersionDao versionDao) {
        super(versionDao);
        this.versionDao = versionDao;
    }

    @Override
    public void addVersionUpdate(ValidatorAddParam param) {
        Version version = new Version();
        version.setVersion(param.getVersion());
        version.setPlatform(param.getPlatform());
        version.setOpenDefi(param.getDefi());
        save(version);
    }

    @Override
    public void updateAppVersion(ValidatorUpdateParam param) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", param.getId());
        Version version = get(versionDao.queryForList(wrapper));
        if (ObjectUtils.isEmpty(version)) {
            throw new BusinessException(CodeRes.CODE_14023);
        }
        version.setVersion(param.getVersion());
        version.setPlatform(param.getPlatform());
        version.setOpenDefi(param.getDefi());
        save(version);

    }

    @Override
    public void delAppVersion(VersionDelParam param) {
        DeleteWrapper wrapper = new DeleteWrapper();
        wrapper.eq("id", param.getId());
        versionDao.executeUpdate(wrapper);
    }

    @Override
    public QueryResult<List<ValidatorListResult>> findAppVersionList(VersionListParam param) {
        QueryWrapper queryWrapper = new QueryWrapper();
        String version = param.getVersion();
        if (!ObjectUtils.isEmpty(version)) {
            queryWrapper.eq("version", version);
        }
        queryWrapper.eq("1","1").orderByDesc("created_date").last(" limit " + param.index() + " , " + param.getPageSize());
        return queryForPage(ValidatorListResult.class, queryWrapper);
    }

    @Override
    public ValidatorListResult findAppVersionDetail(VersionDelParam param) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", param.getId());
        Version version = get(versionDao.queryForList(wrapper));
        ValidatorListResult validatorListResult = new ValidatorListResult();
        BeanUtils.copyProperties(version, validatorListResult);
        return validatorListResult;
    }

    @Override
    public TableBarConfigResult findValidatorStatus(ValidatorStatusParam param) {
        TableBarConfigResult tableBarConfigResult = new TableBarConfigResult();
        PlatformEnum platform = param.getPlatform();
        String versionS = param.getVersion();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("platform", platform.toString()).eq("version", versionS);
        Version version = get(versionDao.queryForList(queryWrapper));
        if (ObjectUtils.isEmpty(version)) {
            tableBarConfigResult.setTopBar(true);
            return tableBarConfigResult;
        }
        tableBarConfigResult.setTopBar(version.getOpenDefi());
        return tableBarConfigResult;
    }
}
