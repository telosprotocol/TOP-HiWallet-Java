package com.topnetwork.system.service;

import java.util.List;

import com.topnetwork.system.entity.User;
import com.topnetwork.wallet.param.system.UserCreateParam;
import com.topnetwork.wallet.param.system.UserDetailParam;
import com.topnetwork.wallet.param.system.UserLoginParam;
import com.topnetwork.wallet.param.system.UserModifyParam;
import com.topnetwork.wallet.param.system.UserModifyPwdParam;
import com.topnetwork.wallet.param.system.UserPageParam;
import com.topnetwork.wallet.param.system.UserRestPwdParam;
import com.topnetwork.wallet.result.common.system.UserDetailResult;
import com.topnetwork.wallet.result.common.system.UserLoginResult;
import com.topnetwork.wallet.result.common.system.UserPageResult;

import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.SqlBaseService;

public interface UserService extends SqlBaseService<User, Long> {

    /**
     * 登陆
     *
     * @param param
     */
    UserLoginResult loginBusiness(UserLoginParam param, String requestIp);

    /**
     * 创建用户
     *
     * @param param
     */
    void createBusiness(UserCreateParam param);

    /**
     * 修改用户
     *
     * @param param
     */
    void modifyBusiness(UserModifyParam param);

    /**
     * 删除用户
     *
     * @param param
     */
    void deleteBusiness(UserDetailParam param);

    /**
     * 用户详情
     *
     * @param param
     */
    UserDetailResult detailBusiness(UserDetailParam param);

    /**
     * 修改密码
     *
     * @param http
     */
    void modifyPwdBusiness(UserModifyPwdParam param, User user);

    /**
     * 重置密码
     *
     * @param param
     */
    void restPwdBusiness(UserRestPwdParam param);

    /**
     * 用户管理
     *
     * @param param
     */
    QueryResult<List<UserPageResult>> userPageBusiness(UserPageParam param);

    /**
     * 根据用户名获取对象
     *
     * @param username
     * @return
     */
    User loadByUserName(String username);

}
