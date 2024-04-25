package com.topnetwork.wallet.result.wallet.discover;

import com.topnetwork.wallet.common.enums.discover.LabelType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @ClassName GetLabelsResult
 * @Description
 * @Author bran
 * @Date 2020/5/19 17:32
 */
public class GetLabelsResult {

    @Schema(title="标签id")
    private Long labelId;
    @Schema(title="标签类型")
    private LabelType labelType;
    @Schema(title="标签各语言名称")
    private List<GetLabelsI18nResult> names;

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

    public List<GetLabelsI18nResult> getNames() {
        return names;
    }

    public void setNames(List<GetLabelsI18nResult> names) {
        this.names = names;
    }
}
