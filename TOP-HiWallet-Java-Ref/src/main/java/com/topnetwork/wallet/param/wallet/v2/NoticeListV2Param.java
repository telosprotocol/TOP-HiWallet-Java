package com.topnetwork.wallet.param.wallet.v2;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.LanguageEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.framework.head.converter.TimeStampConverterEditor;
import com.gitee.magic.core.converter.PropertyConverter;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.NotNull;
import com.gitee.magic.core.valid.annotation.number.LongValid;

import java.util.Date;

public class NoticeListV2Param {

    @Schema(title = "增量获取时间", required = true)
    @NotNull(CodeRes.CODE_15018)
    @LongValid(message = CodeRes.CODE_15018)
    @PropertyConverter(TimeStampConverterEditor.class)
    private Date date;

    @Schema(title = "语言", required = true)
    @NotNull(CodeRes.CODE_14011)
    @Enum(CodeRes.CODE_14011)
    private LanguageEnum language;

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
}
