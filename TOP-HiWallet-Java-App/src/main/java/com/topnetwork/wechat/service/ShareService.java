package com.topnetwork.wechat.service;

import com.topnetwork.wallet.param.wechat.SignatureParam;
import com.topnetwork.wallet.result.wechat.SignatureResult;

public interface ShareService{
    /**
     * Description: 获取签名
     * @param signatureParam
     * @return: com.topnetwork.wallet.result.wechat.SignatureResult
     * @Author: Tyrone
     * @date: 2020/5/6
     **/
    SignatureResult getSignature(SignatureParam signatureParam);
}
