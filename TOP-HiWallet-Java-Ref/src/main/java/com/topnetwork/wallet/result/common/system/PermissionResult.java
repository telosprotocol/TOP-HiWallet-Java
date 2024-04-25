package com.topnetwork.wallet.result.common.system;

import com.topnetwork.wallet.common.enums.MenuEnum;

import io.swagger.v3.oas.annotations.media.Schema;

public class PermissionResult {

    @Schema(title = "权限ID", required = true)
    private Long permissionId;

    @Schema(title = "名称", required = true)
    private String name;

    @Schema(title = "URL", required = true)
    private String url;

    @Schema(title = "所属模板", required = true)
    private MenuEnum module;

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

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

}
