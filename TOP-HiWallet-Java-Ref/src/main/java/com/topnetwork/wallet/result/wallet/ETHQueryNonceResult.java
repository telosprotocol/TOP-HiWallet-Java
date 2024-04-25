package com.topnetwork.wallet.result.wallet;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigInteger;

public class ETHQueryNonceResult {

    @Schema(title = "nonce值", required = true)
    private BigInteger nonce;

    public BigInteger getNonce() {
        return nonce;
    }

    public void setNonce(BigInteger nonce) {
        this.nonce = nonce;
    }
}
