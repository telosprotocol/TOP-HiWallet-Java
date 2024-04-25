package com.topnetwork.wallet.param.wallet.discover;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.discover.LabelType;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

import java.util.List;

/**
 * @ClassName AddLabelParam
 * @Description
 * @Author bran
 * @Date 2020/5/19 17:29
 */
public class AddLabelParam {

    @Schema(title = "标签类型")
    @Enum(CodeRes.CODE_20006)
    @NotNull(CodeRes.CODE_20006)
    @Length(message = CodeRes.CODE_20006)
    private LabelType labelType;

    @Schema(title = "标签名称列表")
    @NotNull(CodeRes.CODE_20007)
    private List<AddLabelI18nParam> names;

    public LabelType getLabelType() {
        return labelType;
    }

    public void setLabelType(LabelType labelType) {
        this.labelType = labelType;
    }

    public List<AddLabelI18nParam> getNames() {
        return names;
    }

    public void setNames(List<AddLabelI18nParam> names) {
        this.names = names;
    }
}
