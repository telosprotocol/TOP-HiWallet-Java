package com.topnetwork.wallet.param.wallet.discover;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName GroupI18nParam
 * @Description
 * @Author bran
 * @Date 2020/5/26 13:43
 */
public class GroupI18nParam {

    @Schema(title = "分组id", required = true)
    @NotNull(CodeRes.CODE_20005)
    @Length(message = CodeRes.CODE_20005)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_20005)
    private Long groupId;
    @Schema(title = "是否显示到首页", required = true)
    @NotNull(CodeRes.CODE_20015)
    @Length(message = CodeRes.CODE_20015)
    @Format(type = Format.FormatType.BOOLEAN, message = CodeRes.CODE_20015)
    private Boolean showToHome;
    @Schema(title = "在该分组内显示排序", required = true)
    @NotNull(CodeRes.CODE_20012)
    @Length(message = CodeRes.CODE_20012)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_20012)
    private Integer showOrder;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Boolean getShowToHome() {
        return showToHome;
    }

    public void setShowToHome(Boolean showToHome) {
        this.showToHome = showToHome;
    }

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }
}
