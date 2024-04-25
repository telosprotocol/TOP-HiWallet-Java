package com.topnetwork.wallet.param.wallet.discover;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.discover.DappShowType;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName AddGroupParam
 * @Description
 * @Author bran
 * @Date 2020/5/19 16:07
 */
public class AddGroupParam {

    @Schema(title = "语言")
    @Enum(CodeRes.CODE_14011)
    @NotNull(CodeRes.CODE_14011)
    @Length(message = CodeRes.CODE_14011)
    private LanguageEnum language;
    @Schema(title = "分组名称")
    @NotNull(CodeRes.CODE_20001)
    @Length(message = CodeRes.CODE_20001)
    private String name;
    @Schema(title = "分组排序")
    @NotNull(CodeRes.CODE_20002)
    @Length(message = CodeRes.CODE_20002)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_20002)
    private Integer groupOrder;
    @Schema(title = "分组是否显示")
    @NotNull(CodeRes.CODE_20003)
    @Length(message = CodeRes.CODE_20003)
    @Format(type = Format.FormatType.BOOLEAN, message = CodeRes.CODE_20003)
    private Boolean groupShow;
    @Schema(title = "展示类型（ 审核时可用：examine, 非审核时可用： normal, 无限制、任何时候都显示： all）")
    @Enum(CodeRes.CODE_15076)
    @NotNull(CodeRes.CODE_15076)
    private DappShowType showType;

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
