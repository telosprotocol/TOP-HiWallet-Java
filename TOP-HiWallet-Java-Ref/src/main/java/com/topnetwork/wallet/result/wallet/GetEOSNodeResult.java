package com.topnetwork.wallet.result.wallet;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName GetEOSNodeResult
 * @Description 获取eos节点列表返回值
 * @Author bran
 * @Date 2020/4/18 10:36
 */
public class GetEOSNodeResult {

    @Schema(title = "节点名称")
    private String nodeName;
    @Schema(title = "节点url")
    private String nodeUrl;

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
}
