package com.topnetwork.wallet.param.wallet.v2;

import com.base.core.head.ao.PageAO;
import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.framework.head.converter.DateTimeConverterEditor;
import com.gitee.magic.core.converter.PropertyConverter;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.TimeFormat;

import java.util.Date;
import java.util.List;

public class CoinHotParam extends PageAO {

    @Schema(title = "更新日期", required = false)
    @TimeFormat(message = CodeRes.CODE_14024)
    @PropertyConverter(DateTimeConverterEditor.class)
    private Date date;

    @Schema(title = "语言（CN：中文，EN：英文）", required = false)
    @Enum(CodeRes.CODE_14011)
    private LanguageEnum language;

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

    public List<String> getChainType() {
        return chainType;
    }

    public void setChainType(List<String> chainType) {
        this.chainType = chainType;
    }
}
