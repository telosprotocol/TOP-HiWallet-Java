package com.topnetwork.wallet.result.wallet;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class BTCTransactionResult {

    private long confirmations;
    @Schema(title = "块高度", required = true)
    private long block_height;
    @Schema(title = "块hash", required = true)
    private String block_hash;
    @Schema(title = "出块时间", required = true)
    private long block_time;
    private long created_at;
    @Schema(title = "交易费", required = true)
    private long fee;
    @Schema(title = "交易hash", required = true)
    private String hash;
    private int inputs_count;
    private int inputs_value;
    private boolean is_coinbase;
    private boolean is_double_spend;
    private boolean is_sw_tx;
    private int weight;
    private int vsize;
    private String witness_hash;
    private int lock_time;
    private int outputs_count;
    private long outputs_value;
    private int size;
    private int sigops;
    private int version;
    @Schema(title = "inputs", required = true)
    private List<BTCInputsResult> inputs;
    @Schema(title = "outputs", required = true)
    private List<BTCOutputsResult> outputs;

    public void setConfirmations(long confirmations) {
        this.confirmations = confirmations;
    }

    public long getConfirmations() {
        return confirmations;
    }

    public void setBlock_height(long block_height) {
        this.block_height = block_height;
    }

    public long getBlock_height() {
        return block_height;
    }

    public void setBlock_hash(String block_hash) {
        this.block_hash = block_hash;
    }

    public String getBlock_hash() {
        return block_hash;
    }

    public void setBlock_time(long block_time) {
        this.block_time = block_time;
    }

    public long getBlock_time() {
        return block_time;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public long getFee() {
        return fee;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }

    public void setInputs_count(int inputs_count) {
        this.inputs_count = inputs_count;
    }

    public int getInputs_count() {
        return inputs_count;
    }

    public void setInputs_value(int inputs_value) {
        this.inputs_value = inputs_value;
    }

    public int getInputs_value() {
        return inputs_value;
    }

    public void setIs_coinbase(boolean is_coinbase) {
        this.is_coinbase = is_coinbase;
    }

    public boolean getIs_coinbase() {
        return is_coinbase;
    }

    public void setIs_double_spend(boolean is_double_spend) {
        this.is_double_spend = is_double_spend;
    }

    public boolean getIs_double_spend() {
        return is_double_spend;
    }

    public void setIs_sw_tx(boolean is_sw_tx) {
        this.is_sw_tx = is_sw_tx;
    }

    public boolean getIs_sw_tx() {
        return is_sw_tx;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setVsize(int vsize) {
        this.vsize = vsize;
    }

    public int getVsize() {
        return vsize;
    }

    public void setWitness_hash(String witness_hash) {
        this.witness_hash = witness_hash;
    }

    public String getWitness_hash() {
        return witness_hash;
    }

    public void setLock_time(int lock_time) {
        this.lock_time = lock_time;
    }

    public int getLock_time() {
        return lock_time;
    }

    public void setOutputs_count(int outputs_count) {
        this.outputs_count = outputs_count;
    }

    public int getOutputs_count() {
        return outputs_count;
    }

    public void setOutputs_value(long outputs_value) {
        this.outputs_value = outputs_value;
    }

    public long getOutputs_value() {
        return outputs_value;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSigops(int sigops) {
        this.sigops = sigops;
    }

    public int getSigops() {
        return sigops;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public void setInputs(List<BTCInputsResult> inputs) {
        this.inputs = inputs;
    }

    public List<BTCInputsResult> getInputs() {
        return inputs;
    }

    public void setOutputs(List<BTCOutputsResult> outputs) {
        this.outputs = outputs;
    }

    public List<BTCOutputsResult> getOutputs() {
        return outputs;
    }

}