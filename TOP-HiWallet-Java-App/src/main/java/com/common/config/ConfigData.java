package com.common.config;

import com.base.core.context.utils.SpringUtils;
import com.base.service.base.system.service.ConfigureService;
import com.base.service.base.system.service.impl.ConfigureServiceImpl;

/**
 * 基础配置中心。可通过刷新指定接口刷新相关配置
 *
 * @author Wyz
 */
public class ConfigData {

    private static ConfigData instance;

    /**
     * 获取单例对象
     *
     * @return
     */
    public static ConfigData get() {
        if (instance == null) {
            reset();
        }
        return instance;
    }

    /**
     * 重置
     */
    public static void reset() {
        ConfigureService configService = SpringUtils.getBean(ConfigureServiceImpl.class);
        instance = configService.initConfig(ConfigData.class,0);
    }

}
