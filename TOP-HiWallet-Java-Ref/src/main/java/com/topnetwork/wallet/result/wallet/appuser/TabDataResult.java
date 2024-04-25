package com.topnetwork.wallet.result.wallet.appuser;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName TabDataResult
 * @Description
 * @Author bran
 * @Date 2020/5/6 18:49
 */
public class TabDataResult {

    @Schema(title = "文案")
    private String text;
    @Schema(title = "邀请 图片 url")
    private String picUrl;
    @Schema(title = "跳转链接")
    private String actionUrl;
    @Schema(title = "是否显示")
    private Boolean tab;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public Boolean getTab() {
        return tab;
    }

    public void setTab(Boolean tab) {
        this.tab = tab;
    }
}
