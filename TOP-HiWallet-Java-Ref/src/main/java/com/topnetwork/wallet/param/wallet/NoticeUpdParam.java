package com.topnetwork.wallet.param.wallet;

import com.topnetwork.wallet.common.enums.PushType;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.framework.head.converter.TimeStampConverterEditor;
import com.gitee.magic.core.converter.PropertyConverter;
import com.gitee.magic.core.valid.annotation.*;

import java.util.Date;

import com.topnetwork.wallet.common.CodeRes;
import com.gitee.magic.core.valid.annotation.Enum;
import com.gitee.magic.core.valid.annotation.number.LongValid;

public class NoticeUpdParam {

    @Schema(title = "公告id", required = true)
    @NotNull(CodeRes.CODE_14001)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_14001)
    private Long id;

    @Schema(title = "标题", required = true)
    @NotNull(CodeRes.CODE_14002)
    @Length(message = CodeRes.CODE_14002, max = 200)
    private String title;

    @Schema(title = "英文标题", required = true)
    @NotNull(CodeRes.CODE_14002)
    @Length(message = CodeRes.CODE_14002, max = 200)
    private String englishTitle;

    @Schema(title = "内容", required = true)
    @NotNull(CodeRes.CODE_14003)
    @Length(message = CodeRes.CODE_14003, max = 2000)
    private String content;

    @Schema(title = "英文内容", required = true)
    @NotNull(CodeRes.CODE_14003)
    @Length(message = CodeRes.CODE_14003, max = 2000)
    private String englishContent;

    @Schema(title = "生效开始时间", required = false)
    @LongValid(message = CodeRes.CODE_14006)
    @PropertyConverter(TimeStampConverterEditor.class)
    private Date startTime;

    @Schema(title = "生效结束时间", required = false)
    @LongValid(message = CodeRes.CODE_14007)
    @PropertyConverter(TimeStampConverterEditor.class)
    private Date endTime;

    @Schema(title = "通知类型", required = true)
    @NotNull(CodeRes.CODE_15014)
    @Enum(CodeRes.CODE_15014)
    private PushType noticeType;

    @Schema(title = "活动url链接", required = false)
    private String url;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public PushType getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(PushType noticeType) {
        this.noticeType = noticeType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
