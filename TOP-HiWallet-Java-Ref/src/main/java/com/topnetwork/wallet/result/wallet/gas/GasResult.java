package com.topnetwork.wallet.result.wallet.gas;

/**
 * @program: TOP-HiWallet-Java
 * @description: ethscan
 * @author: Tyrone
 * @create: 2020-11-11 14:39
 **/
public class GasResult {
    private String status;
    private String message;
    private GasPriceResult result;

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

    public GasPriceResult getResult() {
        return result;
    }

    public void setResult(GasPriceResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "GasResult{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
