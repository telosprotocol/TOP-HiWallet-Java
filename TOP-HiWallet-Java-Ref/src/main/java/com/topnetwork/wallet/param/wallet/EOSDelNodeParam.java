package com.topnetwork.wallet.param.wallet;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName EOSDelNodeParam
 * @Description 删除节点列表参数
 * @Author bran
 * @Date 2020/4/18 13:47
 */
public class EOSDelNodeParam {

    @Schema(title = "节点id", required = true)
    @NotNull(CodeRes.CODE_17006)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_17006)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
