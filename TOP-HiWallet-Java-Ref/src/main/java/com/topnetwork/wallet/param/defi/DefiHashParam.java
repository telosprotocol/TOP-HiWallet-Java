/**
 * 
 */
package com.topnetwork.wallet.param.defi;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotEmpty;

/**
 * 
 * @author jode
 * @date Nov 20, 2020
 */
@Schema(title = "hash参数")
public class DefiHashParam {
	
	@Schema(title = "hash值",required = true)
	@Length
	@NotEmpty
    private String hash;

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
	
}
