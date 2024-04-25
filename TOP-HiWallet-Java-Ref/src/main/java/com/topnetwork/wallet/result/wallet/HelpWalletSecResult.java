package com.topnetwork.wallet.result.wallet;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class HelpWalletSecResult {

    @Schema(title = "一级目录id中文名", required = true)
    private String title_1st;
    @Schema(title = "一级目录id英文名", required = true)
    private String titleEn_1st;
    @Schema(title = "二级目录id中文名", required = true)
    private String title_2nd;
    @Schema(title = "二级目录id英文名", required = true)
    private String titleEn_2nd;

    @Schema(title = "帮助列表", required = true)
    private List<HelpWalletFristResult.Help> list;

    public String getTitle_1st() {
        return title_1st;
    }

    public void setTitle_1st(String title_1st) {
        this.title_1st = title_1st;
    }

    public String getTitleEn_1st() {
        return titleEn_1st;
    }

    public void setTitleEn_1st(String titleEn_1st) {
        this.titleEn_1st = titleEn_1st;
    }

    public String getTitle_2nd() {
        return title_2nd;
    }

    public void setTitle_2nd(String title_2nd) {
        this.title_2nd = title_2nd;
    }

    public String getTitleEn_2nd() {
        return titleEn_2nd;
    }

    public void setTitleEn_2nd(String titleEn_2nd) {
        this.titleEn_2nd = titleEn_2nd;
    }

    public List<HelpWalletFristResult.Help> getList() {
        return list;
    }

    public void setList(List<HelpWalletFristResult.Help> list) {
        this.list = list;
    }
}
