package com.topnetwork.wallet.result.wallet;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName EOSManageNodeResult
 * @Description
 * @Author bran
 * @Date 2020/4/18 11:13
 */
public class EOSManageNodeResult {

    @Schema(title = "id")
    private Long id;
    @Schema(title = "节点名称")
    private String nodeName;
    @Schema(title = "节点Url")
    private String nodeUrl;
    @Schema(title = "节点所处位置")
    private String location;
    @Schema(title = "备注")
    private String comment;
    @Schema(title = "节点是否显示")
    private Boolean disable;
    @Schema(title = "节点排序，小的在前")
    private Integer nodeOrder;
    @Schema(title = "创建时间")
    private Long createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
