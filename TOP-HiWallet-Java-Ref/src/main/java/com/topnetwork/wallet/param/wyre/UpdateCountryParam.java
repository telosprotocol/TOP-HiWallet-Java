package com.topnetwork.wallet.param.wyre;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.NotNull;

import java.util.List;

/**
 * @ClassName UpdateCountryParam
 * @Description
 * @Author bran
 * @Date 2020/8/14 10:30
 */
public class UpdateCountryParam extends AddCountryParam {
    @Schema(title="国家Id")
    @NotNull(CodeRes.CODE_22000)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_22000)
    private Long countryId;

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }
}
