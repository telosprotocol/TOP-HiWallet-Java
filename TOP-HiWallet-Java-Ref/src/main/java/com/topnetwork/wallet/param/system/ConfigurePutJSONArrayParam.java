package com.topnetwork.wallet.param.system;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Format.FormatType;
import com.gitee.magic.core.valid.annotation.NotNull;
import com.gitee.magic.core.json.JsonArray;

/**
 * @author start
 */
public class ConfigurePutJSONArrayParam extends ConfigureParam {
    
    @Schema(title = "配置Value", required = true)
    @NotNull
    @Format(type=FormatType.JSONArray)
    private JsonArray value;

	public JsonArray getValue() {
		return value;
	}

	public void setValue(JsonArray value) {
		this.value = value;
	}

}
