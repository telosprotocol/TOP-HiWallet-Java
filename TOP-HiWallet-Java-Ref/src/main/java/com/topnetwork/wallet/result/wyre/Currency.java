package com.topnetwork.wallet.result.wyre;

import com.base.core.head.converter.UploadKeyConverterEditor;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.converter.PropertyConverter;

/**
 * @ClassName Country
 * @Description
 * @Author bran
 * @Date 2020/8/13 17:46
 */
public class Currency {

    @Schema(title="钱币简写")
    private String currencies;
    @Schema(title="钱币图标图标")
    @PropertyConverter(UploadKeyConverterEditor.class)
    private String currenciesIcon;

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
}
