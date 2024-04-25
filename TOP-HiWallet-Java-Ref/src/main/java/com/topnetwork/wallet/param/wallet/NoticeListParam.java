package com.topnetwork.wallet.param.wallet;

import com.base.core.head.ao.PageAO;

import io.swagger.v3.oas.annotations.media.Schema;

public class NoticeListParam extends PageAO {

    @Schema(title = "标题", required = false)
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
