package com.topnetwork.wallet.param.wyre;

import java.util.List;

import com.base.core.head.valid.UploadKeyValid;
import com.topnetwork.wallet.common.CodeRes;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Custom;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName AddCountryParam
 * @Description
 * @Author bran
 * @Date 2020/8/14 10:31
 */
public class AddCountryParam {


    @Schema(title="国家名称")
    @NotNull(CodeRes.CODE_22005)
    @Length(message = CodeRes.CODE_22005)
    private String name;
    @Schema(title="国家code")
    @NotNull(CodeRes.CODE_22001)
    @Length(message = CodeRes.CODE_22001)
    private String countryCode;
    @Schema(title="国家钱币简称")
    @NotNull(CodeRes.CODE_22002)
    @Length(message = CodeRes.CODE_22002)
    private String currencies;
    @Schema(title="国家钱币图标")
    @Custom(value = UploadKeyValid.class, message = CodeRes.CODE_22003)
    @NotNull(CodeRes.CODE_22003)
    @Length(message = CodeRes.CODE_22003)
    private String currenciesIcon;
    @Schema(title="国家支持兑换币种id列表")
    @NotNull(CodeRes.CODE_22004)
    @Length(message = CodeRes.CODE_22004)
    @Format(type = Format.FormatType.JSONArrayLong, message = CodeRes.CODE_22004)
    private List<Long> coinId;

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

    public List<Long> getCoinId() {
        return coinId;
    }

    public void setCoinId(List<Long> coinId) {
        this.coinId = coinId;
    }
}
