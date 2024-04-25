package com.topnetwork.wallet.param.wallet.top;

import com.topnetwork.wallet.common.CodeRes;
import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;

/**
 * @ClassName TransferParam
 * @Description
 * @Author bran
 * @Date 2020/10/12 15:46
 */
public class TransferParam extends TopBaseParam{

    @Schema(title = "签名后的Model对象字符串", required = true)
    @NotNull(CodeRes.CODE_23006)
    private String requestModel;

    @Schema(title = "top 请求token", required = false)
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRequestModel() {
        return requestModel;
    }

    public void setRequestModel(String requestModel) {
        this.requestModel = requestModel;
    }
}
