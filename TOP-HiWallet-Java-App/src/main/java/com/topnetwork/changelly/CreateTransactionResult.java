package com.topnetwork.changelly;

/**
 * @ClassName CreateTransactionResult
 * @Description
 * @Author bran
 * @Date 2020/6/11 15:21
 */
public class CreateTransactionResult extends ChangellyResponse {

    private CreateTransactionInfo result;

    public CreateTransactionInfo getResult() {
        return result;
    }

    public void setResult(CreateTransactionInfo result) {
        this.result = result;
    }
}
