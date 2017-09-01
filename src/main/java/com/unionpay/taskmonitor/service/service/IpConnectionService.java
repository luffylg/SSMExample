package com.unionpay.taskmonitor.service.service;

/**
 * Created by luffylg on 2017/8/31.
 */
public interface IpConnectionService {
    int queryIpConnection(String ip);
    void updateIpConnection(String ip, String macadress);
    void insertIpConnection(String ip, String macadress);
    void cleantable();
}
