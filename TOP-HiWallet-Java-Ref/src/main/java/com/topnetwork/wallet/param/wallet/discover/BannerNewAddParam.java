package com.topnetwork.wallet.param.wallet.discover;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.common.enums.discover.BannerNewType;
import com.topnetwork.wallet.common.enums.discover.BannerType;
import com.topnetwork.wallet.common.enums.discover.DappShowType;
import com.topnetwork.wallet.common.valid.RegexStr;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.framework.head.converter.TimeStampConverterEditor;
import com.gitee.magic.core.converter.PropertyConverter;
import com.gitee.magic.core.valid.annotation.*;
import com.gitee.magic.core.valid.annotation.Enum;

import java.util.Date;

public class BannerNewAddParam {

    @Schema(title = "标题", required = true)
    @NotNull(CodeRes.CODE_15050)
    @Length(max = 100, message = CodeRes.CODE_15050)
    private String title;

    @Schema(title = "Banner类型", required = true)
    @NotNull(CodeRes.CODE_15051)
    @Enum(CodeRes.CODE_15051)
    private BannerNewType type;

    @Schema(title = "跳转链接地址", required = false)
    @Length(max = 100, message = CodeRes.CODE_19001)
    @Regex(regex = RegexStr.HTTP, message = CodeRes.CODE_19001)
    private String url;

    @Schema(title = "关联应用id", required = false)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_14023)
    private Long dappId;

    @Schema(title = "是否显示", required = true)
    @NotNull(CodeRes.CODE_15053)
    @Format(type = Format.FormatType.BOOLEAN, message = CodeRes.CODE_15053)
    @Length(message = CodeRes.CODE_15053)
    private Boolean bannerShow;


    @Schema(title = "展示类型（ 审核时可用：examine, 非审核时可用： normal, 无限制、任何时候都显示： all）")
    @Enum(CodeRes.CODE_15076)
    @NotNull(CodeRes.CODE_15076)
    private DappShowType showType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BannerNewType getType() {
        return type;
    }

    public void setType(BannerNewType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getDappId() {
        return dappId;
    }

    public void setDappId(Long dappId) {
        this.dappId = dappId;
    }

    public Boolean getBannerShow() {
        return bannerShow;
    }

    public void setBannerShow(Boolean bannerShow) {
        this.bannerShow = bannerShow;
    }

    public DappShowType getShowType() {
        return showType;
    }

    public void setShowType(DappShowType showType) {
        this.showType = showType;
    }
}
