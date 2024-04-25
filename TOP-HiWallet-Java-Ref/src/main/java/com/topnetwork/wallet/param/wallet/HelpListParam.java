package com.topnetwork.wallet.param.wallet;

import com.base.core.head.ao.PageAO;
import com.topnetwork.wallet.common.CodeRes;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;

public class HelpListParam extends PageAO {

    @Schema(title = "一级类目ID", required = false)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_14015)
    private Long category1Id;
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_14015)
    @Schema(title = "二级类目id", required = false)
    private Long category2Id;
    @Schema(title = "标题", required = false)
    private String title;

    public Long getCategory1Id() {
        return category1Id;
    }

    public void setCategory1Id(Long category1Id) {
        this.category1Id = category1Id;
    }

    public Long getCategory2Id() {
        return category2Id;
    }

    public void setCategory2Id(Long category2Id) {
        this.category2Id = category2Id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
