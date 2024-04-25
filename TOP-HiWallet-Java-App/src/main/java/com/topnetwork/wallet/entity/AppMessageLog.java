package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.base.core.framework.sql.entity.BaseExt;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;

@Entity("appMessageLog")
@Table("wal_app_message_log")
@TableDef(comment = "App短信发送记录表")
public class AppMessageLog extends BaseExt {

    private static final long serialVersionUID = 1L;

    public AppMessageLog() {
    }

    @ColumnDef(indexes = @Indexes(normal = @Normal), length = 32, comment = "发送手机号")
    private String mobile;
    @ColumnDef(comment = "发送消息内容")
    private String message;
    @ColumnDef(comment = "语言")
    private LanguageEnum language;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }
}
