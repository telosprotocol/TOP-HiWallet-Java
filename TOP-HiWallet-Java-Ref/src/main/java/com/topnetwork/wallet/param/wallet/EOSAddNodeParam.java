package com.topnetwork.wallet.param.wallet;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName EOSGetNodesParam
 * @Description 新增节点列表参数
 * @Author bran
 * @Date 2020/4/18 11:09
 */
public class EOSAddNodeParam {

    @Schema(title = "节点名称", required = true)
    @NotNull(CodeRes.CODE_17000)
    @Length(message = CodeRes.CODE_17000)
    private String nodeName;
    @Schema(title = "节点Url", required = true)
    @NotNull(CodeRes.CODE_17001)
    @Length(message = CodeRes.CODE_17001)
    private String nodeUrl;
    @Schema(title = "节点所处位置", required = true)
    @NotNull(CodeRes.CODE_17002)
    @Length(message = CodeRes.CODE_17002)
    private String location;
    @Schema(title = "备注", required = true)
    @NotNull(CodeRes.CODE_17003)
    @Length(message = CodeRes.CODE_17003)
    private String comment;
    @Schema(title = "节点是否禁用，true 禁用（不显示） ，false，不禁用（显示）", required = true)
    @NotNull(CodeRes.CODE_17004)
    @Format(type = Format.FormatType.BOOLEAN, message = CodeRes.CODE_17004)
    private Boolean disable;
    @Schema(title = "节点排序，小的在前", required = true)
    @NotNull(CodeRes.CODE_17005)
    @Format(type = Format.FormatType.INTEGER, message = CodeRes.CODE_17005)
    private Integer nodeOrder;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeUrl() {
        return nodeUrl;
    }

    public void setNodeUrl(String nodeUrl) {
        this.nodeUrl = nodeUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public Integer getNodeOrder() {
        return nodeOrder;
    }

    public void setNodeOrder(Integer nodeOrder) {
        this.nodeOrder = nodeOrder;
    }
}
