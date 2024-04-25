package com.common.bussines;

import com.topnetwork.system.entity.User;
import com.topnetwork.wallet.common.CodeRes;

import com.gitee.magic.framework.head.exception.BusinessException;

public class CheckBusiness {

    /**
     * 检测用户信息
     *
     * @param user
     * @return
     */
    public static void checkUser(User user) {
        if (user == null) {
            throw new BusinessException(CodeRes.CODE_11017);
        }
        if (user.getDel()) {
            throw new BusinessException(CodeRes.CODE_11017);
        }
    }

    /**
     * 检测用户信息
     *
     * @param user
     * @return
     */
    public static void checkUserDisable(User user) {
        checkUser(user);
        if (user.getDisable()) {
            throw new BusinessException(CodeRes.CODE_11014);
        }
    }

}
