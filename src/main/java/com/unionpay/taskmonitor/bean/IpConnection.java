package com.unionpay.taskmonitor.bean;

import java.sql.Timestamp;

/**
 * Created by luffylg on 2017/6/29.
 */
public class IpConnection {
    private String ip;
    private String macAdress;
    private Timestamp lastReqTime;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMacAdress() {
        return macAdress;
    }

    public void setMacAdress(String macAdress) {
        this.macAdress = macAdress;
    }

    public Timestamp getLastReqTime() {
        return lastReqTime;
    }

    public void setLastReqTime(Timestamp lastReqTime) {
        this.lastReqTime = lastReqTime;
    }
}
