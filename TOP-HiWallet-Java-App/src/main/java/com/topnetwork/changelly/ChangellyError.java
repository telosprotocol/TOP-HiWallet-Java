package com.topnetwork.changelly;

/**
 * @ClassName ChangellyError
 * @Description
 * @Author bran
 * @Date 2020/6/11 14:36
 */
public class ChangellyError {

    /**
     * 错误码
     */
    private String code;
    /**
     * 错误信息
     */
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
