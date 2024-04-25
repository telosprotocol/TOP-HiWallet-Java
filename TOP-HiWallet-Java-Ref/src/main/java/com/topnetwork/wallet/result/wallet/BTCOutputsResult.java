package com.topnetwork.wallet.result.wallet;

import java.util.List;

public class BTCOutputsResult {

    private List<String> addresses;
    private long value;
    private String type;
    private String script_asm;
    private String script_hex;
    private String spent_by_tx;
    private int spent_by_tx_position;

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setScript_asm(String script_asm) {
        this.script_asm = script_asm;
    }

    public String getScript_asm() {
        return script_asm;
    }

    public void setScript_hex(String script_hex) {
        this.script_hex = script_hex;
    }

    public String getScript_hex() {
        return script_hex;
    }

    public void setSpent_by_tx(String spent_by_tx) {
        this.spent_by_tx = spent_by_tx;
    }

    public String getSpent_by_tx() {
        return spent_by_tx;
    }

    public void setSpent_by_tx_position(int spent_by_tx_position) {
        this.spent_by_tx_position = spent_by_tx_position;
    }

    public int getSpent_by_tx_position() {
        return spent_by_tx_position;
    }

}