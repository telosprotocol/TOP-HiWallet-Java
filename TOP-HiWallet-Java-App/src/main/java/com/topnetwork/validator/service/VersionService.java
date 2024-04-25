package com.topnetwork.validator.service;

import java.util.List;

import com.base.core.framework.sql.service.SqlBaseService;
import com.gitee.magic.framework.base.result.QueryResult;
import com.topnetwork.validator.entity.Version;
import com.topnetwork.wallet.param.wallet.ValidatorUpdateParam;
import com.topnetwork.wallet.param.wallet.VersionDelParam;
import com.topnetwork.wallet.param.wallet.VersionListParam;
import com.topnetwork.wallet.param.wallet.validator.ValidatorAddParam;
import com.topnetwork.wallet.param.wallet.validator.ValidatorStatusParam;
import com.topnetwork.wallet.result.wallet.validator.TableBarConfigResult;
import com.topnetwork.wallet.result.wallet.validator.ValidatorListResult;

public interface VersionService extends SqlBaseService<Version, Long> {
    /**
     * Description: 添加版本信息
     *
     * @param param
     * @return: void
     * @Author: Tyrone
     * @date: 2020/12/3
     **/
    void addVersionUpdate(ValidatorAddParam param);
    /**
     * Description: 更新版本信息
     * @param param
     * @return: void
     * @Author: Tyrone
     * @date: 2020/12/3
     **/
    void updateAppVersion(ValidatorUpdateParam param);
    /**
     * Description: 删除版本信息
     * @param param
     * @return: void
     * @Author: Tyrone
     * @date: 2020/12/3
     **/
    void delAppVersion(VersionDelParam param);
    /**
     * Description: 获取版本列表
     * @param param
     * @return: com.gitee.magic.framework.base.result.QueryResult<java.util.List<com.topnetwork.wallet.result.wallet.validator.ValidatorListResult>>
     * @Author: Tyrone
     * @date: 2020/12/3
     **/
    QueryResult<List<ValidatorListResult>> findAppVersionList(VersionListParam param);
    /**
     * Description: 查询详情
     * @param param
     * @return: com.topnetwork.wallet.result.wallet.validator.ValidatorListResult
     * @Author: Tyrone
     * @date: 2020/12/3
     **/
    ValidatorListResult findAppVersionDetail(VersionDelParam param);
    /**
     * Description: 客户端查询此版本的defi是否开启
     * @param param
     * @return: java.lang.Boolean
     * @Author: Tyrone
     * @date: 2020/12/3
     **/
    TableBarConfigResult findValidatorStatus(ValidatorStatusParam param);
}
