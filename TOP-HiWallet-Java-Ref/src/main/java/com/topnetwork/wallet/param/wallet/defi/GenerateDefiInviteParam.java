package com.topnetwork.wallet.param.wallet.defi;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.valid.RegexStr;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotEmpty;
import com.gitee.magic.core.valid.annotation.Regex;

/**
 * 
 * @author jode
 * @date Oct 29, 2020
 */
public class GenerateDefiInviteParam {

    @Schema(title = "eth地址", required = true)
    @Regex(regex = RegexStr.ETHADDRESS,message = CodeRes.CODE_14036)
    @NotEmpty(CodeRes.CODE_14036)
    private String ethAddress;

	public String getEthAddress() {
		return ethAddress;
	}

	public void setEthAddress(String ethAddress) {
		this.ethAddress = ethAddress;
	}
    
}
