/**
 * 
 */
package com.topnetwork.wallet.common.enums.defi;

/**
 * @author jode
 * @date Nov 19, 2020
 */
public enum SyncHashTypeEnum {
	//OPEN_CONTRACT：开启合约用户可投资，LIQUIDATION_CONTRACT：停止合约/清算;INVESTMENT_CONTRACT: 募集满启动投资操作;APPROVE-usdt授权操作,CANCEL_CONTRACT:取消投资操作
	OPEN_CONTRACT,LIQUIDATION_CONTRACT,INVESTMENT_CONTRACT,APPROVE,CANCEL_CONTRACT

}
