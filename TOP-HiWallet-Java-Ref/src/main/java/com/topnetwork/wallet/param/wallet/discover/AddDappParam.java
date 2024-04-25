package com.topnetwork.wallet.param.wallet.discover;

import java.util.List;

import com.base.core.head.valid.UploadKeyValid;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.common.valid.RegexStr;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Custom;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotEmpty;
import com.gitee.magic.core.valid.annotation.NotNull;
import com.gitee.magic.core.valid.annotation.Regex;

/**
 * @ClassName AddDappParam
 * @Description
 * @Author bran
 * @Date 2020/5/26 15:29
 */
public class AddDappParam {

    @Schema(title = "应用icon", required = true)
    @Custom(value = UploadKeyValid.class, message = CodeRes.CODE_20011)
    @NotEmpty(CodeRes.CODE_20011)
    private String imageUrl;
    @Schema(title = "是否显示", required = true)
    @NotNull(CodeRes.CODE_20012)
    @Length(message = CodeRes.CODE_20012)
    @Format(type = Format.FormatType.BOOLEAN, message = CodeRes.CODE_20012)
    private Boolean appShow;
    @Schema(title = "应用备注标题", required = true)
    @NotEmpty(CodeRes.CODE_20013)
    @Length(max = 100, message = CodeRes.CODE_20013)
    private String title;
    @Schema(title = "主链类型", required = true)
    @NotNull(CodeRes.CODE_15004)
    @Length(message = CodeRes.CODE_15004)
    @Enum(CodeRes.CODE_15004)
    private ChainTypeEnum chainType;
    @Schema(title = "跳转url", required = true)
    @Length(max = 100, message = CodeRes.CODE_19001)
    @Regex(regex = RegexStr.HTTP, message = CodeRes.CODE_19001)
    @NotNull(CodeRes.CODE_19001)
    private String url;
    @Schema(title = "标签列表", required = true)
    @NotNull(CodeRes.CODE_20019)
    private List<AddDappLabelParam> labels;


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getAppShow() {
        return appShow;
    }

    public void setAppShow(Boolean appShow) {
        this.appShow = appShow;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ChainTypeEnum getChainType() {
        return chainType;
    }

    public void setChainType(ChainTypeEnum chainType) {
        this.chainType = chainType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<AddDappLabelParam> getLabels() {
        return labels;
    }

    public void setLabels(List<AddDappLabelParam> labels) {
        this.labels = labels;
    }
}
