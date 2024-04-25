/**
 * 
 */
package com.topnetwork.wallet.result.defi;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author jode
 * @date Jan 18, 2021
 */
public class DefiCheckOrderResult {
	
	@Schema(title = "是否超额（true-超额，给出提示）" , required = true)
	private Boolean over;

	public Boolean getOver() {
		return over;
	}

	public void setOver(Boolean over) {
		this.over = over;
	}
	
}
