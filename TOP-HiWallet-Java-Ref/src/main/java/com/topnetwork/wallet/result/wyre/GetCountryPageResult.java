package com.topnetwork.wallet.result.wyre;

import java.util.List;

import com.base.core.head.converter.UploadKeyConverterEditor;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.converter.PropertyConverter;

/**
 * @ClassName GetCountryPageResult
 * @Description
 * @Author bran
 * @Date 2020/8/14 10:16
 */
public class GetCountryPageResult {

    @Schema(title="国家Id")
    private Long countryId;
    @Schema(title="国家名称")
    private String name;
    @Schema(title="国家code")
    private String countryCode;
    @Schema(title="国家钱币简称")
    private String currencies;
    @Schema(title="国家钱币图标")
    @PropertyConverter(UploadKeyConverterEditor.class)
    private String currenciesIcon;
    @Schema(title="创建时间")
    private Long createTime;
    @Schema(title="国家支持兑换币种")
    private List<CoinResult> coinList;

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCurrencies() {
        return currencies;
    }

    public void setCurrencies(String currencies) {
        this.currencies = currencies;
    }

    public String getCurrenciesIcon() {
        return currenciesIcon;
    }

    public void setCurrenciesIcon(String currenciesIcon) {
        this.currenciesIcon = currenciesIcon;
    }

    public List<CoinResult> getCoinList() {
        return coinList;
    }

    public void setCoinList(List<CoinResult> coinList) {
        this.coinList = coinList;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
