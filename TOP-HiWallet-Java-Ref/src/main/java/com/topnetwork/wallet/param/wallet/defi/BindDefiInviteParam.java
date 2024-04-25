package com.topnetwork.wallet.param.wallet.defi;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.converter.StringUpToLowerConverterEditor;
import com.topnetwork.wallet.common.valid.RegexStr;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.converter.PropertyConverter;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotEmpty;
import com.gitee.magic.core.valid.annotation.Regex;

/**
 * 
 * @author jode
 * @date Oct 29, 2020
 */
public class BindDefiInviteParam {

    @Schema(title = "eth地址", required = true)
    @Regex(regex = RegexStr.ETHADDRESS,message = CodeRes.CODE_14036)
    @NotEmpty(CodeRes.CODE_14036)
    private String ethAddress;
    
    @Schema(title = "绑定的邀请码", required = true)
    @Length(min = 4,max = 4,message = CodeRes.CODE_24000)
    @NotEmpty(CodeRes.CODE_24000)
    @PropertyConverter(StringUpToLowerConverterEditor.class)
    private String bindInviteCode;

	public String getEthAddress() {
		return ethAddress;
	}

	public void setEthAddress(String ethAddress) {
		this.ethAddress = ethAddress;
	}

	public String getBindInviteCode() {
		return bindInviteCode;
	}

	public void setBindInviteCode(String bindInviteCode) {
		this.bindInviteCode = bindInviteCode;
	}
	
}
