package com.topnetwork.wallet.param.filecoin;

import com.topnetwork.wallet.common.CodeRes;
import com.topnetwork.wallet.common.enums.filecoin.SourceEnum;

import io.swagger.v3.oas.annotations.media.Schema;
import com.gitee.magic.core.valid.annotation.Format;
import com.gitee.magic.core.valid.annotation.Length;
import com.gitee.magic.core.valid.annotation.NotNull;
import com.gitee.magic.core.valid.annotation.Regex;

/**
 * @ClassName AddRecoredParam
 * @Description
 * @Author bran
 * @Date 2020/7/15 11:52
 */
public class AddRecordParam {

    @Schema(title = "申购开始时间", required = true)
    @NotNull(CodeRes.CODE_15018)
    @Length(message = CodeRes.CODE_15018)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_15018)
    private Long startTime;
    @Schema(title = "申购结束时间", required = true)
    @NotNull(CodeRes.CODE_15018)
    @Length(message = CodeRes.CODE_15018)
    @Format(type = Format.FormatType.LONG, message = CodeRes.CODE_15018)
    private Long endTime;
    @Schema(title = "付款地址", required = true)
    @NotNull(CodeRes.CODE_15006)
    @Length(message = CodeRes.CODE_15006)
    @Regex(regex = "^0x[a-fA-F0-9]{40}$", message = CodeRes.CODE_15006)
    private String payAddress;
    @Schema(title = "收款地址", required = true)
    @NotNull(CodeRes.CODE_15006)
    @Length(message = CodeRes.CODE_15006)
    @Regex(regex = "^0x[a-fA-F0-9]{40}$", message = CodeRes.CODE_15006)
    private String receiveAddress;
    @Schema(title = "认购数量", required = true)
    @Format(type = Format.FormatType.GT_ZERO, message = CodeRes.CODE_15011)
    @NotNull(CodeRes.CODE_15011)
    @Length(message = CodeRes.CODE_15011)
    private Long amount;
    @Schema(title = "邮箱", required = true)
    @Format(type = Format.FormatType.MAIL, message = CodeRes.CODE_21021)
    @NotNull(CodeRes.CODE_21021)
    @Length(message = CodeRes.CODE_21021)
    private String email;
    @Schema(title = "手机号码", required = true)
    @Format(type = Format.FormatType.MOBILE, message = CodeRes.CODE_11013)
    @NotNull(CodeRes.CODE_11013)
    @Length(message = CodeRes.CODE_11013)
    private String mobile;
    @Schema(title = "微信号", required = true)
    @NotNull(CodeRes.CODE_21022)
    @Length(message = CodeRes.CODE_21022)
    private String wechat;
    @Schema(title = "staking 地址")
    @Regex(regex = "^0x[a-fA-F0-9]{40}$", message = CodeRes.CODE_15006)
    private String stakingAddress;
    @Schema(title = "申请来源", required = true)
    @NotNull(CodeRes.CODE_21023)
    @Length(message = CodeRes.CODE_21023)
    private SourceEnum source;

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getPayAddress() {
        return payAddress;
    }

    public void setPayAddress(String payAddress) {
        this.payAddress = payAddress;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getStakingAddress() {
        return stakingAddress;
    }

    public void setStakingAddress(String stakingAddress) {
        this.stakingAddress = stakingAddress;
    }

    public SourceEnum getSource() {
        return source;
    }

    public void setSource(SourceEnum source) {
        this.source = source;
    }
}
