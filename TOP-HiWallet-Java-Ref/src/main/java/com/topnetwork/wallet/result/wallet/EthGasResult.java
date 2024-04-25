package com.topnetwork.wallet.result.wallet;

import java.io.Serializable;

public class EthGasResult implements Serializable {
    private String fastest;
    private String safeLow;
    private String average;
    private String safeLowWait;
    private String avgWait;
    private String fastWait;

    public String getFastest() {
        return fastest;
    }

    public void setFastest(String fastest) {
        this.fastest = fastest;
    }

    public String getSafeLow() {
        return safeLow;
    }

    public void setSafeLow(String safeLow) {
        this.safeLow = safeLow;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getSafeLowWait() {
        return safeLowWait;
    }

    public void setSafeLowWait(String safeLowWait) {
        this.safeLowWait = safeLowWait;
    }

    public String getAvgWait() {
        return avgWait;
    }

    public void setAvgWait(String avgWait) {
        this.avgWait = avgWait;
    }

    public String getFastWait() {
        return fastWait;
    }

    public void setFastWait(String fastWait) {
        this.fastWait = fastWait;
    }

    @Override
    public String toString() {
        return "EthGas{" +
                "fastest='" + fastest + '\'' +
                ", safeLow='" + safeLow + '\'' +
                ", average='" + average + '\'' +
                ", safeLowWait='" + safeLowWait + '\'' +
                ", avgWait='" + avgWait + '\'' +
                ", fastWait='" + fastWait + '\'' +
                '}';
    }
}

