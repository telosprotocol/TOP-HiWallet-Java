package com.topnetwork.wallet.result.changelly;

import com.base.core.head.converter.UploadKeyConverterEditor;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.converter.PropertyConverter;

/**
 * @ClassName GetCoinListResult
 * @Description
 * @Author bran
 * @Date 2020/6/15 16:41
 */
public class GetCoinListResult {
    @Schema(title = "币种名称")
    private String symbol;
    @Schema(title = "币种id")
    private Long coinId;
    @Schema(title = "图标地址")
    @PropertyConverter(UploadKeyConverterEditor.class)
    private String logoUrl;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getCoinId() {
        return coinId;
    }

    public void setCoinId(Long coinId) {
        this.coinId = coinId;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
