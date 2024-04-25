package com.topnetwork.wallet.result.wallet.top;

import java.math.BigDecimal;

import com.topnetwork.wallet.common.enums.top.TopTxStatus;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName GetTxList
 * @Description
 * @Author bran
 * @Date 2020/10/12 11:16
 */
public class GetTxListResult {

    @Schema(title="对方账户")
    private String to;
    @Schema(title="发起账户")
    private String from;
    @Schema(title="交易金额")
    private String amount;
    @Schema(title="交易状态")
    private TopTxStatus status;
    @Schema(title="交易时间")
    private Long timestamp;
    @Schema(title="交易类型")
    private String txType;
    @Schema(title="交易手续费")
    private BigDecimal gasUsed;
    @Schema(title="交易备注")
    private String note;
    @Schema(title="交易hash")
    private String hash;
    @Schema(title="交易保证金")
    private BigDecimal txDeposit;
    @Schema(title="交易使用的保证金")
    private BigDecimal usedDeposit;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public TopTxStatus getStatus() {
        return status;
    }

    public void setStatus(TopTxStatus status) {
        this.status = status;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTxType() {
		return txType;
	}

	public void setTxType(String txType) {
		this.txType = txType;
	}

	public BigDecimal getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(BigDecimal gasUsed) {
        this.gasUsed = gasUsed;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public BigDecimal getTxDeposit() {
        return txDeposit;
    }

    public void setTxDeposit(BigDecimal txDeposit) {
        this.txDeposit = txDeposit;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public BigDecimal getUsedDeposit() {
        return usedDeposit;
    }

    public void setUsedDeposit(BigDecimal usedDeposit) {
        this.usedDeposit = usedDeposit;
    }
}
