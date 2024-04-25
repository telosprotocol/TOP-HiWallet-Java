package com.topnetwork.filecoin.entity;

import com.base.core.framework.sql.entity.BaseExt;
import com.gitee.magic.core.annotations.Column;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;

@Entity("tempData")
@Table("fil_temp_data")
@TableDef(comment = "filecoin活动临时数据表")
public class TempData extends BaseExt {

    private static final long serialVersionUID = 1L;

    public TempData() {
    }

    @ColumnDef(comment = "数据cid")
    private String cid;
    @ColumnDef(comment = "类型", isNull = true)
    private String type;
    @ColumnDef(comment = "文件大小", isNull = true)
    private String size;
    @ColumnDef(comment = "文件名", isNull = true)
    @Column("file_name")
    private String fileName;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
