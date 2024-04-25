/**
 * 
 */
package com.topnetwork.wallet.result.defi;

import com.topnetwork.wallet.common.converter.StringUpToLowerConverterEditor;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.converter.PropertyConverter;

/**
 * @author jode
 * @date Oct 29, 2020
 */
public class DefiInviteResult {
	
	@Schema(title = "eth地址" , required = true)
	private String ethAddress;
	
	@Schema(title = "当前地址的邀请码", required = true)
	@PropertyConverter(StringUpToLowerConverterEditor.class)
    private String inviteCode;
	
	@Schema(title="绑定的邀请码")
	@PropertyConverter(StringUpToLowerConverterEditor.class)
    private String bindInviteCode;

	public String getEthAddress() {
		return ethAddress;
	}

	public void setEthAddress(String ethAddress) {
		this.ethAddress = ethAddress;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getBindInviteCode() {
		return bindInviteCode;
	}

	public void setBindInviteCode(String bindInviteCode) {
		this.bindInviteCode = bindInviteCode;
	}

	@Override
	public String toString() {
		return "DefiInviteResult [ethAddress=" + ethAddress + ", inviteCode=" + inviteCode + ", bindInviteCode="
				+ bindInviteCode + "]";
	}
	
}
