package com.topnetwork.wallet.param.wallet.discover;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.discover.LabelType;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

import java.util.List;

/**
 * @ClassName UpdateLabelParam
 * @Description
 * @Author bran
 * @Date 2020/5/19 17:30
 */
public class UpdateLabelParam {

    @Schema(title = "标签ID")
    @NotNull(CodeRes.CODE_20008)
    @Length(message = CodeRes.CODE_20008)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_20008)
    private Long labelId;
    @Schema(title = "标签类型")
    @Enum(CodeRes.CODE_20006)
    @NotNull(CodeRes.CODE_20006)
    @Length(message = CodeRes.CODE_20006)
    private LabelType labelType;
    @Schema(title = "标签名称列表")
    @NotNull(CodeRes.CODE_20007)
    private List<UpdLabelI18nParam> names;

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public LabelType getLabelType() {
        return labelType;
    }

    public void setLabelType(LabelType labelType) {
        this.labelType = labelType;
    }

    public List<UpdLabelI18nParam> getNames() {
        return names;
    }

    public void setNames(List<UpdLabelI18nParam> names) {
        this.names = names;
    }
}
