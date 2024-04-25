package com.topnetwork.wallet.entity;

import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Normal;

import java.util.Date;

@Entity("eosNode")
@Table("wal_eos_node")
@TableDef(comment = "eos 节点列表")
public class EOSNode extends Base {

    private static final long serialVersionUID = 1L;

    public EOSNode() {
    }

    @ColumnDef(comment = "节点名称", indexes = @Indexes(normal = @Normal))
    private String nodeName;
    @ColumnDef(comment = "节点Url")
    private String nodeUrl;
    @ColumnDef(comment = "节点所处位置")
    private String location;
    @ColumnDef(comment = "备注")
    private String comment;
    @ColumnDef(comment = "节点是否禁用，true 禁用（不显示） ，false，不禁用（显示）")
    private Boolean disable;
    @ColumnDef(comment = "节点排序，小的在前")
    private Integer nodeOrder;
    @ColumnDef(comment = "创建时间")
    private Date createTime;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeUrl() {
        return nodeUrl;
    }

    public void setNodeUrl(String nodeUrl) {
        this.nodeUrl = nodeUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public Integer getNodeOrder() {
        return nodeOrder;
    }

    public void setNodeOrder(Integer nodeOrder) {
        this.nodeOrder = nodeOrder;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
