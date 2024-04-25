package com.topnetwork.common.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.core.context.annotation.RestfulCheck;
import com.base.core.context.mvc.BaseController;
import com.base.service.base.system.entity.SecretKeyDO;
import com.base.service.base.system.service.SecretKeyService;
import com.common.config.ConfigData;
import com.gitee.magic.core.exception.ApplicationException;
import com.gitee.magic.core.utils.codec.Md5;
import com.gitee.magic.framework.base.result.BaseResponse;
import com.gitee.magic.framework.base.result.ResultResponse;
import com.gitee.magic.framework.head.utils.TimeUtils;
import com.topnetwork.common.service.SecurityService;
import com.topnetwork.system.entity.Permission;
import com.topnetwork.system.entity.User;
import com.topnetwork.system.service.PermissionService;
import com.topnetwork.system.service.UserPermissionService;
import com.topnetwork.system.service.UserService;
import com.topnetwork.wallet.common.enums.MenuEnum;
import com.topnetwork.wallet.common.enums.RoleEnum;
import com.topnetwork.wallet.result.wallet.verify.SecurityMarkImageResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/common")
@Tag(name = "配置")
@CrossOrigin
public class ConfigController extends BaseController {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private UserService userService;
    @Autowired
    private SecretKeyService secretKeyService;
    @Autowired
    private UserPermissionService userPermissionService;
    @Autowired
    private SecurityService securityService;

    @Operation(summary="刷新配置")
    @GetMapping("/reset_config_data")
    public BaseResponse reset_config_data() {
        ConfigData.reset();
        return response();
    }

    @Operation(summary="数据生成")
    @GetMapping("/build_data")
    public BaseResponse build_data() throws Exception {
        //用户
        User user = new User();
        user.setUserName("admin");
        user.setName("超级管理员");
        user.setMobile("13012344321");
        user.setPassword(Md5.md5("123123"));
        user.setRole(RoleEnum.ADMIN);
        user.setCreateTime(new Date());
        user.setDisable(false);
        user.setDel(false);
        if (userService.save(user) == 0) return response();
        //密钥
        SecretKeyDO key = new SecretKeyDO();
        key.setAccessId("8412f654f8662d033111fc453edc5b63");
        key.setAccessKey("c4jWPrC8mdRC+Nt3ftdwDDi564O3L0+FqMjRRHwHigBwmmoSZB7Pug==");
        key.setInvalidTime(TimeUtils.format("20210101000000", TimeUtils.YYYYMMDDHHMMSS));
        key.setType(0);
        key.setRemark("开发");
        if (secretKeyService.save(key) == 0) return response();
        //权限生成
        List<Long> permissionIds = new ArrayList<>();
        Map<String, Object> map = context.getBeansWithAnnotation(Controller.class);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Class<?> prototype = entry.getValue().getClass();
            if (prototype.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping mapping = prototype.getAnnotation(RequestMapping.class);
                for (String url1 : mapping.value()) {
                    for (Method method : prototype.getMethods()) {
//                        AuthenticationCheck auth = method.getAnnotation(AuthenticationCheck.class);
                        //跳过不需要授权的列表
//                        if (auth == null || auth.value() != Auth.CHECK) continue;
                        Operation operation = method.getAnnotation(Operation.class);
                        if (operation == null) {
                            System.out.println("请配置类：" + prototype.getName() + ",方法：" + method.getName() + ",ApiOperation注解");
                            continue;
                        }
                        List<String> mappings = new ArrayList<>();
                        if (method.isAnnotationPresent(RequestMapping.class)) {
                            mappings.addAll(Arrays.asList(method.getAnnotation(RequestMapping.class).value()));
                        }
                        if (method.isAnnotationPresent(PostMapping.class)) {
                            mappings.addAll(Arrays.asList(method.getAnnotation(PostMapping.class).value()));
                        }
                        for (String url2 : mappings) {
                            Permission permission = new Permission();
                            permission.setName(operation.summary());
                            permission.setUrl(url1 + url2);
                            permission.setModule(MenuEnum.NONE);
                            permission.setSort(0);
                            permission.setDisable(false);
                            permissionService.save(permission);
                            permissionIds.add(permission.getId());
                        }
                    }
                }
            }
        }
        //用户权限映射
        userPermissionService.updatePermissionMapping(user.getId(), permissionIds);
        return response();
    }

    @CrossOrigin
    @Operation(summary="生成校验图片")
    @RestfulCheck
    @GetMapping("/image/mark")
    public ResultResponse<SecurityMarkImageResult> markImage() {
        BufferedImage orgImage = null;
        BufferedImage topCoin = null;
        try {
            orgImage = ImageIO.read(new ClassPathResource("data/img/background.png").getInputStream());
            topCoin = ImageIO.read(new ClassPathResource("data/img/bucket.png").getInputStream());
        } catch (IOException e) {
            throw new ApplicationException(e);
        }
        return response(securityService.buildRandomImage(orgImage, topCoin));
    }

}
