package com.topnetwork.wallet.param.defi;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

public class DefiLoginParam {

	@Schema(title = "地址")
	@Length
	@NotNull
    private String address;
	
	@Schema(title = "来源")
	@NotNull
	private String source;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
}
