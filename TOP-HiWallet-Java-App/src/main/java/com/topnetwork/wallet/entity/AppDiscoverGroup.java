package com.topnetwork.wallet.entity;

import com.topnetwork.wallet.common.enums.LanguageEnum;
import com.topnetwork.wallet.common.enums.discover.DappShowType;
import com.base.core.framework.sql.entity.BaseExt;
import com.gitee.magic.jdbc.persistence.annotation.Entity;
import com.gitee.magic.jdbc.persistence.annotation.Table;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.ColumnDef;
import com.gitee.magic.jdbc.persistence.source.jdbc.script.annotations.TableDef;

@Entity("appDiscoverGroup")
@Table("wal_app_discover_group")
@TableDef(comment = "发现分组")
public class AppDiscoverGroup extends BaseExt {

    private static final long serialVersionUID = 1L;

    public AppDiscoverGroup() {
    }

    @ColumnDef(comment = "语言")
    private LanguageEnum language;
    @ColumnDef(comment = "分组名称")
    private String name;
    @ColumnDef(comment = "分组排序")
    private Integer groupOrder;
    @ColumnDef(comment = "分组是否显示")
    private Boolean groupShow;
    @ColumnDef(comment = "展示类型（ 审核时可用：examine, 非审核时可用： normal, 无限制、任何时候都显示： all）")
    private DappShowType showType;

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(Integer groupOrder) {
        this.groupOrder = groupOrder;
    }

    public Boolean getGroupShow() {
        return groupShow;
    }

    public void setGroupShow(Boolean groupShow) {
        this.groupShow = groupShow;
    }

    public DappShowType getShowType() {
        return showType;
    }

    public void setShowType(DappShowType showType) {
        this.showType = showType;
    }
}
