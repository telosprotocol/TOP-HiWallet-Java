package com.topnetwork.wallet.result.wallet.discover;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.discover.DappShowType;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName GetGroupsResult
 * @Description
 * @Author bran
 * @Date 2020/5/19 16:12
 */
public class GetGroupsResult {

    @Schema(title = "分组id")
    private Long id;
    @Schema(title = "语言")
    private LanguageEnum language;
    @Schema(title = "分组名称")
    private String name;
    @Schema(title = "分组排序")
    private Integer groupOrder;
    @Schema(title = "分组是否显示")
    private Boolean groupShow;
    @Schema(title = "展示类型（ 审核时可用：examine, 非审核时可用： normal, 无限制、任何时候都显示： all）")
    private DappShowType showType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(Integer groupOrder) {
        this.groupOrder = groupOrder;
    }

    public Boolean getGroupShow() {
        return groupShow;
    }

    public void setGroupShow(Boolean groupShow) {
        this.groupShow = groupShow;
    }

    public DappShowType getShowType() {
        return showType;
    }

    public void setShowType(DappShowType showType) {
        this.showType = showType;
    }
}
