package com.topnetwork.wallet.result.wallet.validator;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @program: TOP-HiWallet-Java
 * @description:
 * @author: Tyrone
 * @create: 2020-12-03 18:20
 **/
public class TableBarConfigResult {

    @Schema(title = "defi开关", required = true)
    private Boolean topBar;

    public Boolean getTopBar() {
        return topBar;
    }

    public void setTopBar(Boolean topBar) {
        this.topBar = topBar;
    }

    @Override
    public String toString() {
        return "TableBarConfigResult{" +
                "topBar=" + topBar +
                '}';
    }
}
