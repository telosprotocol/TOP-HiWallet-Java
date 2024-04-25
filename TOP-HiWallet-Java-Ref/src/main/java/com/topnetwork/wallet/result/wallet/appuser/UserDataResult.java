package com.topnetwork.wallet.result.wallet.appuser;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName UserDataResult
 * @Description
 * @Author bran
 * @Date 2020/3/30 18:53
 */
public class UserDataResult implements Serializable {

    private static final long serialVersionUID = 15674756757567L;

    @Schema(title = "accessId")
    private String accessId;
    @Schema(title = "accessKey")
    private String accessKey;
    @Schema(title = "用户id")
    private Long id;
    @Schema(title = "用户uid")
    private String uid;
    @Schema(title = "电话")
    private String mobile;
    @Schema(title = "等级")
    private Integer level;

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
