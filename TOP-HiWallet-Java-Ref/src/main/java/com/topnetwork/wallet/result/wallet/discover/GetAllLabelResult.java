package com.topnetwork.wallet.result.wallet.discover;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName GetAllLabelResult
 * @Description
 * @Author bran
 * @Date 2020/5/26 14:39
 */
public class GetAllLabelResult {

    @Schema(title="标签id")
    private Long labelId;
    @Schema(title="标签名字")
    private String name;

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
