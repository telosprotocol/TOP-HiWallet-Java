package com.topnetwork.wallet.param.wallet.discover;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.discover.BannerNewType;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName GetAppDetailParam
 * @Description 上报用户dapp使用记录参数
 * @Author bran
 * @Date 2020/4/22 13:48
 */
public class GetAppDetailParam {

    @Schema(title = "dapp id", required = true)
    @NotNull(CodeRes.CODE_15041)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_15041)
    private Long dappId;
    @Schema(title = "类型 TOPSTAKING,//top staking ；HTML,跳转至H5，用内置浏览器打开； CENTRALIAPP,跳转至新青年中心化app；DECENTRALIAPP,跳转至DApp等去中心化app；HTMLDAPP,H5带详情", required = true)
    @NotNull(CodeRes.CODE_15051)
    @Enum(CodeRes.CODE_15051)
    private BannerNewType type;

    @Schema(title = "语言", required = true)
    @Enum(CodeRes.CODE_14011)
    @NotNull(CodeRes.CODE_14011)
    @Length(message = CodeRes.CODE_14011)
    private LanguageEnum language;


    public Long getDappId() {
        return dappId;
    }

    public void setDappId(Long dappId) {
        this.dappId = dappId;
    }

    public BannerNewType getType() {
        return type;
    }

    public void setType(BannerNewType type) {
        this.type = type;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }
}
