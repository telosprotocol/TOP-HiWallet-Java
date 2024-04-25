package com.topnetwork.wallet.common.enums.defi;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "产品状态枚举")
public enum ContractProductStatusEnum {
	//INIT：预热中；RAISE_PROCESSING：募集中；RAISE_FULL：募集满；MINING：挖矿中；SETTLEMENT_IN_PROGRESS：结算中；END：结束,CANCEL：取消
	RAISE_PROCESSING,RAISE_FULL,INIT,MINING,SETTLEMENT_IN_PROGRESS,END,CANCEL;

}
