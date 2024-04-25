package com.topnetwork.changelly;

import java.io.Serializable;

/**
 * @ClassName ValidateInfo
 * @Description
 * @Author bran
 * @Date 2020/6/11 15:16
 */
public class ValidateInfo implements Serializable {
    private static final long serialVersionUID = 43948582629964283L;
    /**
     * 是否可用（是否正确）
     */
    private Boolean result;
    /**
     * 错误提示
     */
    private String message;

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Boolean getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
