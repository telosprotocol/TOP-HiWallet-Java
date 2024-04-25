package com.common.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import com.base.common.mvc.BaseInterceptor;
import com.base.core.context.annotation.SecretKeyAuthorityCheck;
import com.common.bussines.CheckBusiness;
import com.base.service.base.logger.entity.LoginLogDO;
import com.base.service.base.logger.service.LoginLogService;
import com.base.service.base.system.entity.SecretKeyDO;
import com.topnetwork.system.entity.User;
import com.topnetwork.system.service.PermissionService;
import com.topnetwork.system.service.UserService;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.entity.AppUser;
import com.topnetwork.wallet.entity.SecretPlatform;
import com.topnetwork.wallet.service.AppUserService;
import com.topnetwork.wallet.service.SecretPlatformService;

import com.gitee.magic.framework.base.context.Http;
import com.gitee.magic.framework.head.exception.BusinessException;

@Component
public class AuthorityInterceptor extends BaseInterceptor {

    @Autowired
    private UserService userService;
    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private SecretPlatformService secretPlatformService;
    @Autowired
    private AppUserService appUserService;

    @Override
    public void authenticationCheck(Http http, HandlerMethod handlerMethod) {
        // 后台管理
        LoginLogDO log = loginLogService.authorityCheck(http);
        User user = userService.load(log.getUserId());
        CheckBusiness.checkUserDisable(user);
        permissionService.authorityCheck(user.getId(), http.getRequest().getServletPath());
        http.putCache(log);
        http.putCache(user);
    }

    @Override
    public void secretKeyAuthorityCheck(Http http, HandlerMethod handlerMethod) {
        SecretKeyAuthorityCheck check = handlerMethod.getMethod().getAnnotation(SecretKeyAuthorityCheck.class);
        http.requestCheck();
        SecretKeyDO secretKey = secretKeyService.getSecretKeyByAccessIdWithType(http.getAccessId(), check.value());
        SecretPlatform secretPlatform = secretPlatformService.findByAccessId(http.getAccessId());
        if (secretPlatform != null) {
            http.putCache(secretPlatform);
        }
//        http.signaturesHmacSHA1Check(secretKey.getAccessKey());
    }

    @Override
    public void loginAuthorityCheck(Http http, HandlerMethod handlerMethod) {
        // 钱包登录
        LoginLogDO log = loginLogService.authorityCheck(http);
        AppUser user = appUserService.findById(log.getUserId());
        if (user.getDisable()) {
            throw new BusinessException(CodeRes.CODE_17007);
        }
        http.putCache(user);
    }


}
