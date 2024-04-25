package com.topnetwork.wallet.result.wallet;

public class BTCTransactionHTTPResult {

    private int err_no;
    private BTCTransactionResult data;

    public void setErr_no(int err_no) {
        this.err_no = err_no;
    }

    public int getErr_no() {
        return err_no;
    }

    public void setData(BTCTransactionResult data) {
        this.data = data;
    }

    public BTCTransactionResult getData() {
        return data;
    }

}