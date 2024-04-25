package com.topnetwork.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.core.mvc.business.CommonBusiness;
import com.common.bussines.CheckBusiness;
import com.base.service.base.logger.entity.LoginLogDO;
import com.base.service.base.logger.service.LoginLogService;
import com.topnetwork.system.dao.UserDao;
import com.topnetwork.system.entity.User;
import com.topnetwork.system.service.PermissionService;
import com.topnetwork.system.service.UserPermissionService;
import com.topnetwork.system.service.UserService;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.param.system.UserCreateParam;
import com.topnetwork.wallet.param.system.UserDetailParam;
import com.topnetwork.wallet.param.system.UserLoginParam;
import com.topnetwork.wallet.param.system.UserModifyParam;
import com.topnetwork.wallet.param.system.UserModifyPwdParam;
import com.topnetwork.wallet.param.system.UserPageParam;
import com.topnetwork.wallet.param.system.UserRestPwdParam;
import com.topnetwork.wallet.result.common.system.PermissionResult;
import com.topnetwork.wallet.result.common.system.UserDetailResult;
import com.topnetwork.wallet.result.common.system.UserLoginResult;
import com.topnetwork.wallet.result.common.system.UserPageResult;

import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.framework.base.result.QueryResult;
import com.base.core.framework.sql.service.impl.SqlBaseServiceImpl;
import com.gitee.magic.jdbc.persistence.source.jdbc.sqlplus.conditions.query.QueryWrapper;

@Service("userService")
public class UserServiceImpl extends SqlBaseServiceImpl<User, Long> implements UserService {

    private UserDao userDao;
    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private UserPermissionService userPermissionService;

    public UserServiceImpl(@Qualifier("userDao") UserDao userDao) {
        super(userDao);
        this.userDao = userDao;
    }

    @Override
    public UserLoginResult loginBusiness(UserLoginParam param, String requestIp) {
        User user = loadByUserName(param.getUserName());
        CheckBusiness.checkUserDisable(user);
        if (!param.getPassword().equals(user.getPassword())) {
            throw new BusinessException(CodeRes.CODE_11015);
        }
//        LoginLog loginLog = new LoginLog();
//        loginLog.setUserId(user.getId());
//        loginLog.setRequestIp(requestIp);
//        loginLog.setSource(SourceEnum.WEB);
//        loginLog.setType("MANAGER");
        LoginLogDO loginLog =loginLogService.buildLog(user.getId(),requestIp,"WEB","MANAGER");
        UserLoginResult result = userInfoResult(user);
        result.setAccessid(loginLog.getAccessId());
        result.setAccesskey(loginLog.getAccessKey());
        return result;
    }

    @Transactional
    @Override
    public void createBusiness(UserCreateParam param) {
        // 判断用户是否已经存在
        if (loadByUserName(param.getUserName()) != null) {
            throw new BusinessException(CodeRes.CODE_11019);
        }

        if (loadByMobile(param.getMobile()) != null) {
            throw new BusinessException(CodeRes.CODE_11013);
        }
        User user = new User();
        user.setUserName(param.getUserName());
        user.setPassword(param.getPassword());
        user.setCreateTime(new Date());
        user.setName(param.getName());
        user.setMobile(param.getMobile());
        user.setRole(param.getRole());
        user.setDisable(param.getDisable());
        user.setDel(false);
        save(user);
        userPermissionService.updatePermissionMapping(user.getId(), param.getPermissionIds());
    }

    @Transactional
    @Override
    public void modifyBusiness(UserModifyParam param) {
        User user = load(param.getUserId());
        CheckBusiness.checkUser(user);
        User u = new User();
        u.setId(user.getId());
        u.setName(param.getName());
        u.setMobile(param.getMobile());
        u.setRole(param.getRole());
        u.setDisable(param.getDisable());
        save(u);
        userPermissionService.updatePermissionMapping(u.getId(), param.getPermissionIds());
    }

    @Override
    public void deleteBusiness(UserDetailParam param) {
        User user = load(param.getUserId());
        CheckBusiness.checkUser(user);
        User u = new User();
        u.setId(param.getUserId());
        u.setDel(true);
        save(u);
    }

    @Override
    public void restPwdBusiness(UserRestPwdParam param) {
        User user = load(param.getUserId());
        CheckBusiness.checkUser(user);
        User u = new User();
        u.setId(param.getUserId());
        u.setPassword(param.getPassword());
        save(u);
    }

    @Override
    public UserDetailResult detailBusiness(UserDetailParam param) {
        User user = load(param.getUserId());
        CheckBusiness.checkUser(user);
        return userInfoResult(user);
    }

    @Override
    public void modifyPwdBusiness(UserModifyPwdParam param, User user) {
        if (!param.getOldpassword().equals(user.getPassword())) {
            throw new BusinessException(CodeRes.CODE_11015);
        }
        User u = new User();
        u.setId(user.getId());
        u.setPassword(param.getNewpassword());
        save(u);
    }

    @Override
    public QueryResult<List<UserPageResult>> userPageBusiness(UserPageParam param) {
        Map<String, Object> params = CommonBusiness.httpPageParam(param);
        return queryForPage(UserPageResult.class, "get_user_page", params);
    }

    /**
     * 返回用户信息
     *
     * @param user
     * @return
     */
    private UserLoginResult userInfoResult(User user) {
        UserLoginResult userInfo = new UserLoginResult();
        userInfo.setUserId(user.getId());
        userInfo.setName(user.getName());
        userInfo.setUserName(user.getUserName());
        userInfo.setMobile(user.getMobile());
        userInfo.setRole(user.getRole());
        userInfo.setDisable(user.getDisable());
        userInfo.setCreateTime(user.getCreateTime());
        // 权限列表
        List<PermissionResult> permissions = permissionService.getPermissionWithUserId(user.getId());
        if (!permissions.isEmpty()) {
            userInfo.setPermissions(permissions);
        }
        return userInfo;
    }

    @Override
    public User loadByUserName(String username) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        return get(userDao.queryForList(wrapper));
    }

    public User loadByMobile(String mobile) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("mobile", mobile);
        return get(userDao.queryForList(wrapper));
    }

}
