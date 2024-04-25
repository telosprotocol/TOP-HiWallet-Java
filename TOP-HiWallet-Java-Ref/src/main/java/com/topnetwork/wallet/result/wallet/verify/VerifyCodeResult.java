package com.topnetwork.wallet.result.wallet.verify;

import com.base.core.head.converter.UploadKeyConverterEditor;
import com.topnetwork.wallet.common.enums.verify.VerifyCodeStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.converter.PropertyConverter;

/**
 * @program: topverificationcode
 * @description:
 * @author: Tyrone
 * @create: 2020-03-16 17:15
 **/
public class VerifyCodeResult {
    @Schema(title = "主键ID", required = true)
    private Long id;

    @Schema(title = "imgKey(缩略图地址)", required = true)
    @PropertyConverter(UploadKeyConverterEditor.class)
    private String imgKey;

    @Schema(title = "缩略图名称", required = true)
    private String name;

    @Schema(title = "创建时间", required = true)
    private Long createDate;

    @Schema(title = "是否启用", required = true)
    private VerifyCodeStatus verifyCodeStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgKey() {
        return imgKey;
    }

    public void setImgKey(String imgKey) {
        this.imgKey = imgKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public VerifyCodeStatus getVerifyCodeStatus() {
        return verifyCodeStatus;
    }

    public void setVerifyCodeStatus(VerifyCodeStatus verifyCodeStatus) {
        this.verifyCodeStatus = verifyCodeStatus;
    }

    @Override
    public String toString() {
        return "VerifyCodeResult{" +
                "id=" + id +
                ", imgKey='" + imgKey + '\'' +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                ", verifyCodeStatus=" + verifyCodeStatus +
                '}';
    }
}
