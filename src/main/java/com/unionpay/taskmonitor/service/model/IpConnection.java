package com.unionpay.taskmonitor.service.model;

import java.util.Date;

public class IpConnection {
    private String ip;

    private String macadress;

    private Date lastreqtime;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getMacadress() {
        return macadress;
    }

    public void setMacadress(String macadress) {
        this.macadress = macadress == null ? null : macadress.trim();
    }

    public Date getLastreqtime() {
        return lastreqtime;
    }

    public void setLastreqtime(Date lastreqtime) {
        this.lastreqtime = lastreqtime;
    }
}