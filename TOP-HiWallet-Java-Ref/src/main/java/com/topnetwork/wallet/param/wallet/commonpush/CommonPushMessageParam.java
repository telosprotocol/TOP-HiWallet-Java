package com.topnetwork.wallet.param.wallet.commonpush;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotNull;

import java.util.List;

/**
 * Description:
 *
 * @param null
 * @return:
 * @Author: Tyrone
 * @date: 2021/1/15
 **/
public class CommonPushMessageParam {


    @Schema(title = "通知内容", required = true)
    @NotNull(CodeRes.CODE_40003)
    private String content;

    @Schema(title = "通知标题", required = true)
    @NotNull(CodeRes.CODE_40004)
    private String title;

    @Schema(title = "英文通知内容", required = true)
    @NotNull(CodeRes.CODE_40005)
    private String englishContent;

    @Schema(title = "英文通知标题", required = true)
    @NotNull(CodeRes.CODE_40006)
    private String englishTitle;

    @Schema(title = "以太坊地址list", required = true)
    @NotNull(CodeRes.CODE_40007)
    private List<String> addressList;

    @Schema(title = "推送Url", required = true)
    @NotNull(CodeRes.CODE_40017)
    private String url;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEnglishContent() {
        return englishContent;
    }

    public void setEnglishContent(String englishContent) {
        this.englishContent = englishContent;
    }

    public String getEnglishTitle() {
        return englishTitle;
    }

    public void setEnglishTitle(String englishTitle) {
        this.englishTitle = englishTitle;
    }

    public List<String> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<String> addressList) {
        this.addressList = addressList;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "DefiPushMessageParam{" +
                "content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", englishContent='" + englishContent + '\'' +
                ", englishTitle='" + englishTitle + '\'' +
                ", addressList=" + addressList +
                ", url='" + url + '\'' +
                '}';
    }
}
