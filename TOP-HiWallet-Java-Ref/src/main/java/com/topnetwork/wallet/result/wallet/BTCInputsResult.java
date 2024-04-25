package com.topnetwork.wallet.result.wallet;

import java.util.List;

public class BTCInputsResult {

    private List<String> prev_addresses;
    private int prev_position;
    private String prev_tx_hash;
    private String prev_type;
    private int prev_value;
    private long sequence;
    private String script_asm;
    private String script_hex;
    private List<String> witness;

    public void setPrev_addresses(List<String> prev_addresses) {
        this.prev_addresses = prev_addresses;
    }

    public List<String> getPrev_addresses() {
        return prev_addresses;
    }

    public void setPrev_position(int prev_position) {
        this.prev_position = prev_position;
    }

    public int getPrev_position() {
        return prev_position;
    }

    public void setPrev_tx_hash(String prev_tx_hash) {
        this.prev_tx_hash = prev_tx_hash;
    }

    public String getPrev_tx_hash() {
        return prev_tx_hash;
    }

    public void setPrev_type(String prev_type) {
        this.prev_type = prev_type;
    }

    public String getPrev_type() {
        return prev_type;
    }

    public void setPrev_value(int prev_value) {
        this.prev_value = prev_value;
    }

    public int getPrev_value() {
        return prev_value;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public long getSequence() {
        return sequence;
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

    public void setWitness(List<String> witness) {
        this.witness = witness;
    }

    public List<String> getWitness() {
        return witness;
    }

}