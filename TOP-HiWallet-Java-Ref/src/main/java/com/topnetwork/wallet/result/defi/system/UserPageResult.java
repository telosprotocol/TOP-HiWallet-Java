package com.topnetwork.wallet.result.defi.system;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.framework.head.converter.DateTimeConverterEditor;
import com.gitee.magic.core.converter.PropertyConverter;

/**
 * 用户管理页
 *
 * @author Start
 */
public class UserPageResult {

    @Schema(title="用户ID")
    private Long userId;

    @Schema(title="账号")
    private String userName;

    @Schema(title="姓名")
    private String name;

    @Schema(title="创建时间")
    @PropertyConverter(DateTimeConverterEditor.class)
    private Date createdDate;

    @Schema(title="是否禁用")
    private Boolean disable;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

}
