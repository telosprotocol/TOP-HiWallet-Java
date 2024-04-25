package com.topnetwork.wallet.param.system;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Format.FormatType;
import com.gitee.magic.core.valid.annotation.NotNull;
import com.gitee.magic.core.json.JsonObject;

/**
 * @author start
 */
public class ConfigurePutJSONObjectParam extends ConfigureParam {
    
    @Schema(title = "配置Value", required = true)
    @NotNull
    @Format(type=FormatType.JSONObject)
    private JsonObject value;

	public JsonObject getValue() {
		return value;
	}

	public void setValue(JsonObject value) {
		this.value = value;
	}

}
