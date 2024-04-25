package com.common.core;

import com.base.core.mvc.web.ExceptionController;
import com.base.core.mvc.web.LoggerRequestWrapper;
import com.gitee.magic.framework.base.constant.Message;
import com.gitee.magic.framework.base.context.Http;
import com.gitee.magic.framework.base.context.HttpHolder;
import com.gitee.magic.framework.head.vo.BaseVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

/**
 * @author JodeXu
 * @className WalletExceptionController
 * @Description 描述
 * @date 2023/2/14 6:29 PM
 */
@RestControllerAdvice
public class WalletExceptionController extends ExceptionController {
    @Override
    protected Object response(String name, Message msg, HandlerMethod handlerMethod, Throwable e, Boolean isSend) {
        try {
            LoggerRequestWrapper.print(request,e,isSend);
            if (isMethodAuthCheck(handlerMethod)) {
                Http http = getHttp();
                return response(http, msg, name);
            } else {
                String requestId= HttpHolder.getRequestId(request);
                return new BaseVO(msg.getCode(), msg.getMessage(), requestId, name);
            }
        }finally {
            response.setStatus(HttpStatus.OK.value());
        }
    }
}
