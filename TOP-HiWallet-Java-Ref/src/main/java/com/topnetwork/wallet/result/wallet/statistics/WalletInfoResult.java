package com.topnetwork.wallet.result.wallet.statistics;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName WalletInfoResult
 * @Description
 * @Author bran
 * @Date 2020/6/23 17:55
 */
public class WalletInfoResult {

    @Schema(title="助记词数量")
    private Integer mnemonicWords;
    @Schema(title="设备数量")
    private Integer device;
    @Schema(title="地址数量")
    private Integer address;

    public Integer getMnemonicWords() {
        return mnemonicWords;
    }

    public void setMnemonicWords(Integer mnemonicWords) {
        this.mnemonicWords = mnemonicWords;
    }

    public Integer getDevice() {
        return device;
    }

    public void setDevice(Integer device) {
        this.device = device;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }
}
