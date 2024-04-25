package com.topnetwork.wallet.param.system;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.NotEmpty;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @author start
 */
public class ConfigurePutStringParam extends ConfigureParam {
    
    @Schema(title = "配置Value", required = true)
    @NotNull
    @NotEmpty
    private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
