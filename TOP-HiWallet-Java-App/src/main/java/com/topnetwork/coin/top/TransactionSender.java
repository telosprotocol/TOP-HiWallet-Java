package com.topnetwork.coin.top;

import java.util.Map;

import org.topj.methods.response.ResponseBase;
import org.topj.methods.response.XTransaction;
import org.topj.methods.response.tx.XTransactionResponse;
import org.topj.procotol.http.HttpService;
import org.topj.utils.ArgsUtils;

import com.gitee.magic.core.exception.ApplicationException;

/**
 * @ClassName TransactionSender
 * @Description
 * @Author bran
 * @Date 2020/10/13 18:22
 */
public class TransactionSender {

    public static ResponseBase<XTransactionResponse> _sendTxCommon(HttpService httpService, Map<String, String> argsMap) {

        try {
            ResponseBase<XTransactionResponse> responseBase;
            responseBase = httpService.send(argsMap, XTransactionResponse.class);
            XTransaction xTransaction = ArgsUtils.decodeXTransFromArgs(argsMap);
            XTransactionResponse xTransactionResponse = new XTransactionResponse();
            xTransactionResponse.setOriginalTxInfo(xTransaction);
            responseBase.setData(xTransactionResponse);
            return responseBase;
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

}
