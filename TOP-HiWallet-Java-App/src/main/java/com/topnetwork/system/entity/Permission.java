package com.topnetwork.system.entity;

import com.topnetwork.wallet.common.enums.MenuEnum;

import com.base.core.framework.sql.entity.Base;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.Indexes;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.indexes.Unique;

@Entity("permission")
@Table("sys_permission")
@TableDef(comment = "权限")
public class Permission extends Base {

    private static final long serialVersionUID = 1L;

    public Permission() {
    }

    @ColumnDef(length = 50, comment = "名称")
    private String name;

    @ColumnDef(indexes = @Indexes(unique = @Unique), comment = "URL")
    private String url;

    @ColumnDef(comment = "模块")
    private MenuEnum module;

    @ColumnDef(comment = "排序")
    private Integer sort;

    @ColumnDef(comment = "是否禁用")
    private Boolean disable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MenuEnum getModule() {
        return module;
    }

    public void setModule(MenuEnum module) {
        this.module = module;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

}
