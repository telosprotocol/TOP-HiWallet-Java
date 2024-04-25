package com.topnetwork.wallet.param.wallet.v2;

import com.base.core.head.ao.PageAO;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.framework.head.converter.DateTimeConverterEditor;
import com.gitee.magic.core.converter.PropertyConverter;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.TimeFormat;

import java.util.Date;
import java.util.List;

public class CoinSpeciesParam extends PageAO {

    @Schema(title = "更新日期", required = false)
    @TimeFormat(message = CodeRes.CODE_14024)
    @PropertyConverter(DateTimeConverterEditor.class)
    private Date date;

    @Schema(title = "语言（CN，EN）", required = false)
    @Enum(CodeRes.CODE_14011)
    private LanguageEnum language;

    @Schema(title = "币种名字,或者合约地址", required = false)
    private String coinName;

    @Schema(title = "是否是主币, ture是主链， false是token", required = false)
    @Format(type = Format.FormatType.BOOLEAN, message = CodeRes.CODE_14017)
    private Boolean mainChain;

    @Schema(title = "字段标识主链类型，比如 以太坊主链是ETH", required = false)
    private List<String> chainType;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public Boolean getMainChain() {
        return mainChain;
    }

    public void setMainChain(Boolean mainChain) {
        this.mainChain = mainChain;
    }

    public List<String> getChainType() {
        return chainType;
    }

    public void setChainType(List<String> chainType) {
        this.chainType = chainType;
    }
}
