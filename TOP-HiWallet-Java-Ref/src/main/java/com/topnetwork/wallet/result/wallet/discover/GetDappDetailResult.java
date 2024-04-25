package com.topnetwork.wallet.result.wallet.discover;

import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @ClassName GetDappDetailResult
 * @Description
 * @Author bran
 * @Date 2020/5/26 15:06
 */
public class GetDappDetailResult extends GetAppDetailBaseResult {

    @Schema(title="主链类型")
    private ChainTypeEnum chainType;
    @Schema(title="跳转链接")
    private String url;
    @Schema(title="标签列表")
    private List<GetAllLabelResult> labels;

    public ChainTypeEnum getChainType() {
        return chainType;
    }

    public void setChainType(ChainTypeEnum chainType) {
        this.chainType = chainType;
    }

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
}
