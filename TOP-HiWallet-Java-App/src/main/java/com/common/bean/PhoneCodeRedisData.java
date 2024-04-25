package com.common.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName PhoneCodeRedisData
 * @Description
 * @Author bran
 * @Date 2020/5/7 16:55
 */
public class PhoneCodeRedisData implements Serializable {

    private static final long serialVersionUID = 448582643529964283L;

    //已发送次数
    private Integer times;
    //最后一次验证码发送时间
    private Date lastSendTime;
    //验证码
    private String code;
    //当前验证码错误次数
    private Integer errorTimes;

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getLastSendTime() {
        return lastSendTime;
    }

    public void setLastSendTime(Date lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

    public Integer getErrorTimes() {
        return errorTimes;
    }

    public void setErrorTimes(Integer errorTimes) {
        this.errorTimes = errorTimes;
    }

    @Override
    public String toString() {
        return "PhoneCodeRedisData{" +
                "times=" + times +
                ", lastSendTime=" + lastSendTime +
                ", code='" + code + '\'' +
                ", errorTimes=" + errorTimes +
                '}';
    }
}
