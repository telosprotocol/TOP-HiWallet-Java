package com.topnetwork.wallet.result.topgame;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName CheckWalletAddressResulr
 * @Description
 * @Author bran
 * @Date 2020/8/12 17:22
 */
public class CheckWalletAddressResult {

    @Schema(title="是否钱账号")
    private Boolean walletAddress;

    public Boolean getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(Boolean walletAddress) {
        this.walletAddress = walletAddress;
    }
}
