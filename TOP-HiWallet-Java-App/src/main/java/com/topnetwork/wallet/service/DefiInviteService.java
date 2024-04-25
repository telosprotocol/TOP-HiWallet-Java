package com.topnetwork.wallet.service;

import com.topnetwork.wallet.entity.DefiInvite;
import com.topnetwork.wallet.param.wallet.defi.BindDefiInviteParam;
import com.topnetwork.wallet.param.wallet.defi.GenerateDefiInviteParam;
import com.topnetwork.wallet.result.defi.DefiInviteResult;

import com.base.core.framework.sql.service.SqlBaseService;

public interface DefiInviteService extends SqlBaseService<DefiInvite,Long> {

	/**
	 * 生成邀请码并返回到客户端
	 * @param params
	 * @return
	 */
	DefiInviteResult generate(GenerateDefiInviteParam params);

	/**
	 * 绑定邀请码
	 * @param params
	 */
	void bind(BindDefiInviteParam params);

}
