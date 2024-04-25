package com.topnetwork.wallet.param.wallet;

import com.topnetwork.wallet.common.CodeRes;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.NotNull;

public class HelpUpdParam {


    @Schema(title = "帮助id", required = true)
    @NotNull(CodeRes.CODE_14008)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_14008)
    private Long id;

    @Schema(title = "标题", required = true)
    @NotNull(CodeRes.CODE_14009)
    private String title;

    @Schema(title = "标题", required = true)
    @NotNull(CodeRes.CODE_14009)
    private String englishTitle;

    @Schema(title = "主题内容", required = true)
    @NotNull(CodeRes.CODE_14010)
    private String content;

    @Schema(title = "主题内容", required = true)
    @NotNull(CodeRes.CODE_14010)
    private String englishContent;

    @Schema(title = "一级标题id", required = true)
    @NotNull(CodeRes.CODE_14015)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_14015)
    private Long stCategoryId;

    @Schema(title = "二级标题id", required = true)
    @NotNull(CodeRes.CODE_14015)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_14015)
    private Long ndCategoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getStCategoryId() {
        return stCategoryId;
    }

    public void setStCategoryId(Long stCategoryId) {
        this.stCategoryId = stCategoryId;
    }

    public Long getNdCategoryId() {
        return ndCategoryId;
    }

    public void setNdCategoryId(Long ndCategoryId) {
        this.ndCategoryId = ndCategoryId;
    }

    public String getEnglishTitle() {
        return englishTitle;
    }

    public void setEnglishTitle(String englishTitle) {
        this.englishTitle = englishTitle;
    }

    public String getEnglishContent() {
        return englishContent;
    }

    public void setEnglishContent(String englishContent) {
        this.englishContent = englishContent;
    }
}
