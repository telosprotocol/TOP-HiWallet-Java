package com.topnetwork.wallet.result.wallet.discover;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @ClassName GetDappDetailResult
 * @Description
 * @Author bran
 * @Date 2020/5/26 15:06
 */
public class GetManAppDetailResult extends GetAppDetailBaseResult {

    @Schema(title="下载地址")
    private String url;
    @Schema(title="备用下载地址")
    private String subUrl;
    @Schema(title="标签列表")
    private List<GetAllLabelResult> labels;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<GetAllLabelResult> getLabels() {
        return labels;
    }

    public void setLabels(List<GetAllLabelResult> labels) {
        this.labels = labels;
    }

    public String getSubUrl() {
        return subUrl;
    }

    public void setSubUrl(String subUrl) {
        this.subUrl = subUrl;
    }
}
