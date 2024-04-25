package com.topnetwork.wallet.result.wallet.appuser;

import com.base.core.head.enums.ValueTypeEnum;

import io.swagger.v3.oas.annotations.media.Schema;

public class ConfigPageResult {

    @Schema(title = "序号（id）")
    private Long id;
    @Schema(title = "编号")
    private String code;
    @Schema(title = "名称")
    private String name;
    @Schema(title = "值")
    private String value;
    @Schema(title = "类型")
    private ValueTypeEnum type;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ValueTypeEnum getType() {
        return type;
    }

    public void setType(ValueTypeEnum type) {
        this.type = type;
    }
}
