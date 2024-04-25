/**
 * 
 */
package com.common.bean;

/**
 * @author jode
 *
 */
public class TransactionPageVO {

	private String amount;

	private Long lastTxNonce;

	private Long tableHeight;

	private String receiverAccount;

	private String senderAccount;

	private Long sendTimestamp;

	private String tokenName;

	private String txHash;
	
    private String txType;

	private String txState;
	
	private Integer senderTableId;
	
	private Integer receiverTableId;
	
	private String account;
	
    private Long txDeposit;
	
    private Long usedDeposit;
	
    private Long usedGas;
	
    private String note;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Long getLastTxNonce() {
		return lastTxNonce;
	}

	public void setLastTxNonce(Long lastTxNonce) {
		this.lastTxNonce = lastTxNonce;
	}

	public Long getTableHeight() {
		return tableHeight;
	}

	public void setTableHeight(Long tableHeight) {
		this.tableHeight = tableHeight;
	}

	public String getReceiverAccount() {
		return receiverAccount;
	}

	public void setReceiverAccount(String receiverAccount) {
		this.receiverAccount = receiverAccount;
	}

	public String getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(String senderAccount) {
		this.senderAccount = senderAccount;
	}

	public Long getSendTimestamp() {
		return sendTimestamp;
	}

	public void setSendTimestamp(Long sendTimestamp) {
		this.sendTimestamp = sendTimestamp;
	}

	public String getTokenName() {
		return tokenName;
	}

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	public String getTxHash() {
		return txHash;
	}

	public void setTxHash(String txHash) {
		this.txHash = txHash;
	}

	public String getTxType() {
		return txType;
	}

	public void setTxType(String txType) {
		this.txType = txType;
	}

	public String getTxState() {
		if(txState != null || txState != ""){
			if(txState.equals("fail")){
				return "failure";
			}
		}
		return txState;
	}

	public void setTxState(String txState) {
		this.txState = txState;
	}

	public Integer getSenderTableId() {
		return senderTableId;
	}

	public void setSenderTableId(Integer senderTableId) {
		this.senderTableId = senderTableId;
	}

	public Integer getReceiverTableId() {
		return receiverTableId;
	}

	public void setReceiverTableId(Integer receiverTableId) {
		this.receiverTableId = receiverTableId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Long getTxDeposit() {
		return txDeposit;
	}

	public void setTxDeposit(Long txDeposit) {
		this.txDeposit = txDeposit;
	}

	public Long getUsedDeposit() {
		return usedDeposit;
	}

	public void setUsedDeposit(Long usedDeposit) {
		this.usedDeposit = usedDeposit;
	}

	public Long getUsedGas() {
		return usedGas;
	}

	public void setUsedGas(Long usedGas) {
		this.usedGas = usedGas;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
    
}
