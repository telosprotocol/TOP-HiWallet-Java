package com.topnetwork.wallet.common.enums.withdraw;

public enum OrderStatus {
    /**
     * 审核中：review
     * <p>
     * 审核通过，到账中：processing
     * <p>
     * 被驳回：rejected
     * <p>
     * 成功：success
     * <p>
     * 失败：failed
     */
    review, processing, rejected, success, failed
}
