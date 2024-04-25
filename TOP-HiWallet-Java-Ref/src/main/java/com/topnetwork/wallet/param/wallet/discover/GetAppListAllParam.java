package com.topnetwork.wallet.param.wallet.discover;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.discover.BannerNewType;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName GetAppListParam
 * @Description
 * @Author bran
 * @Date 2020/5/25 17:11
 */
public class GetAppListAllParam {

    @Schema(title="banner类型")
    @NotNull(CodeRes.CODE_20010)
    @Enum(CodeRes.CODE_20010)
    private BannerNewType bannerType;

    public BannerNewType getBannerType() {
        return bannerType;
    }

    public void setBannerType(BannerNewType bannerType) {
        this.bannerType = bannerType;
    }
}
