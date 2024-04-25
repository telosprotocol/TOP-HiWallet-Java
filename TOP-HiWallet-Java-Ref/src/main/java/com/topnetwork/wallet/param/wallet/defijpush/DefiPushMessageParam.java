package com.topnetwork.wallet.param.wallet.defijpush;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotNull;

import java.util.HashMap;
import java.util.List;

/**
 * @program: TOP-HiWallet-Java
 * @description:
 * @author: Tyrone
 * @create: 2020-11-27 15:28
 **/
public class DefiPushMessageParam {


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

    @Override
    public String toString() {
        return "DefiPushMessageParam{" +
                "content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", englishContent='" + englishContent + '\'' +
                ", englishTitle='" + englishTitle + '\'' +
                ", addressList=" + addressList +
                '}';
    }
}
