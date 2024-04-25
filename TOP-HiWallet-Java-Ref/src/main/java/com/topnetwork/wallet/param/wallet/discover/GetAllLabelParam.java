package com.topnetwork.wallet.param.wallet.discover;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.discover.LabelType;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName GetAllLabelParam
 * @Description
 * @Author bran
 * @Date 2020/5/26 14:47
 */
public class GetAllLabelParam {

    @Schema(title = "标签类型,DAPP标签,APPLICATION：应用标签", required = true)
    @Enum(CodeRes.CODE_20006)
    @NotNull(CodeRes.CODE_20006)
    @Length(message = CodeRes.CODE_20006)
    private LabelType labelType;

    public LabelType getLabelType() {
        return labelType;
    }

    public void setLabelType(LabelType labelType) {
        this.labelType = labelType;
    }
}
