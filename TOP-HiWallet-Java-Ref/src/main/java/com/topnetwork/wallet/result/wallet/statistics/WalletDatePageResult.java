package com.topnetwork.wallet.result.wallet.statistics;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName WalletDatePageResult
 * @Description
 * @Author bran
 * @Date 2020/6/23 18:28
 */
public class WalletDatePageResult extends WalletInfoResult {

    @Schema(title="日期字符串")
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
