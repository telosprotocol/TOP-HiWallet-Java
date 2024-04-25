package com.topnetwork.changelly;

/**
 * @ClassName ValidateAddressResult
 * @Description
 * @Author bran
 * @Date 2020/6/11 15:17
 */
public class ValidateAddressResult extends ChangellyResponse {

    private ValidateInfo result;

    public ValidateInfo getResult() {
        return result;
    }

    public void setResult(ValidateInfo result) {
        this.result = result;
    }
}
