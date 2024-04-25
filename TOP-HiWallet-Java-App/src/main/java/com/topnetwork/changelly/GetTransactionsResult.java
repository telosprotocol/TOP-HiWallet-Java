package com.topnetwork.changelly;

import java.util.List;

/**
 * @ClassName GetTransactionsResult
 * @Description
 * @Author bran
 * @Date 2020/6/11 15:28
 */
public class GetTransactionsResult extends ChangellyResponse {

    private List<TransactionInfo> result;

    public List<TransactionInfo> getResult() {
        return result;
    }

    public void setResult(List<TransactionInfo> result) {
        this.result = result;
    }
}
