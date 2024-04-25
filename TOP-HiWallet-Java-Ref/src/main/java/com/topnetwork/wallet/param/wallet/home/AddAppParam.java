package com.topnetwork.wallet.param.wallet.home;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.discover.AppType;
import com.topnetwork.wallet.common.enums.discover.DappShowType;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName AddAppParam
 * @Description
 * @Author bran
 * @Date 2020/9/24 17:14
 */
public class AddAppParam {

    @Schema(title = "关联应用id", required = true)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_14023)
    @NotNull(CodeRes.CODE_14023)
    @Length(message = CodeRes.CODE_14023)
    private Long appId;
    @Schema(title = "app类型 PRO;HTML;DAPP;APP", required = true)
    @Enum(CodeRes.CODE_20010)
    @NotNull(CodeRes.CODE_20010)
    @Length(message = CodeRes.CODE_20010)
    private AppType appType;
    @Schema(title = "显示顺序", required = true)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_15054)
    @NotNull(CodeRes.CODE_15054)
    @Length(message = CodeRes.CODE_15054)
    private Integer appOrder;
    @Schema(title = "展示类型（ 审核时可用：examine, 非审核时可用： normal, 无限制、任何时候都显示： all）")
    @Enum(CodeRes.CODE_15076)
    @NotNull(CodeRes.CODE_15076)
    private DappShowType showType;

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public AppType getAppType() {
        return appType;
    }

    public void setAppType(AppType appType) {
        this.appType = appType;
    }

    public Integer getAppOrder() {
        return appOrder;
    }

    public void setAppOrder(Integer appOrder) {
        this.appOrder = appOrder;
    }

    public DappShowType getShowType() {
        return showType;
    }

    public void setShowType(DappShowType showType) {
        this.showType = showType;
    }
}
