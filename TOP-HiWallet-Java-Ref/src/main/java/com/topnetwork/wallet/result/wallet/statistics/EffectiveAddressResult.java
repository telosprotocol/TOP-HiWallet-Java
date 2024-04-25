package com.topnetwork.wallet.result.wallet.statistics;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName EffectiveAddressResult
 * @Description
 * @Author bran
 * @Date 2020/6/22 14:23
 */
public class EffectiveAddressResult {

    @Schema(title="币种名字")
    private String name;
    @Schema(title="所有地址统计")
    private AddressResult all;
    @Schema(title="存币地址统计")
    private AddressResult effective;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressResult getAll() {
        return all;
    }

    public void setAll(AddressResult all) {
        this.all = all;
    }

    public AddressResult getEffective() {
        return effective;
    }

    public void setEffective(AddressResult effective) {
        this.effective = effective;
    }
}
