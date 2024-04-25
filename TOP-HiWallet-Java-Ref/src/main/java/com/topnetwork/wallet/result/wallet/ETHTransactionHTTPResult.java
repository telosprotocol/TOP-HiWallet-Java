package com.topnetwork.wallet.result.wallet;

import java.util.List;

public class ETHTransactionHTTPResult {

    private String status;
    private String message;
    private List<ETHTransactionResult> result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ETHTransactionResult> getResult() {
        return result;
    }

    public void setResult(List<ETHTransactionResult> result) {
        this.result = result;
    }
}
