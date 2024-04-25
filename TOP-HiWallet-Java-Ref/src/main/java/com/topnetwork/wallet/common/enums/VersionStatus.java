package com.topnetwork.wallet.common.enums;

/**
 * @ClassName VersionStatus
 * @Description 版本更新状态
 * @Author bran
 * @Date 2020/3/31 16:54
 */
public enum VersionStatus {
    /**
     * 无更新
     */
    NOUPDATE,
    /**
     * 强制更新
     */
    FORCE,
    /**
     * 弹框更新
     */
    FRAME,
    /**
     * 静默（红点）更新
     */
    SILENCE,
    /**
     * 公告更新
     */
    NOTICE
}
