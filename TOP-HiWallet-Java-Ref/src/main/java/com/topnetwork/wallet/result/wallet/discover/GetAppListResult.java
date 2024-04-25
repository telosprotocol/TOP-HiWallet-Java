package com.topnetwork.wallet.result.wallet.discover;

import com.topnetwork.wallet.common.enums.ChainTypeEnum;
import com.topnetwork.wallet.common.enums.discover.AppType;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName GetAppListResult
 * @Description
 * @Author bran
 * @Date 2020/5/25 17:12
 */
public class GetAppListResult extends GetAppDetailBaseResult {

    @Schema(title="主链类型 TOP,EOS,ETH,,,")
    private ChainTypeEnum chainType;

    public ChainTypeEnum getChainType() {
        return chainType;
    }

    public void setChainType(ChainTypeEnum chainType) {
        this.chainType = chainType;
    }

}
