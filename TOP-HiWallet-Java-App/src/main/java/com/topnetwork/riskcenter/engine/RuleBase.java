package com.topnetwork.riskcenter.engine;

import com.gitee.magic.framework.base.context.Cache;

/**
 * 风控规则接口
 * @author bran
 */
public interface RuleBase {

    /**
     * 校验方法
     *
     * @param cache
     * @return
     */
    boolean check(Cache cache);
}
