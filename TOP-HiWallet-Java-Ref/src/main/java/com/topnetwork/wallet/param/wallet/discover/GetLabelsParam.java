package com.topnetwork.wallet.param.wallet.discover;

import com.base.core.head.ao.PageAO;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.discover.LabelType;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName GetLabelsParam
 * @Description
 * @Author bran
 * @Date 2020/5/19 17:29
 */
public class GetLabelsParam extends PageAO {

    @Schema(title = "标签类型")
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
