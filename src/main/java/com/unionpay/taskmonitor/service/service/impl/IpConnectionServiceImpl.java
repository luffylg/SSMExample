package com.unionpay.taskmonitor.service.service.impl;

import com.unionpay.taskmonitor.service.dao.IpConnectionMapper;
import com.unionpay.taskmonitor.service.model.IpConnection;
import com.unionpay.taskmonitor.service.model.IpConnectionExample;
import com.unionpay.taskmonitor.service.service.IpConnectionService;
import com.unionpay.taskmonitor.utils.PropertiesHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Properties;

/**
 * Created by luffylg on 2017/9/1.
 */
@Service("ipConnectionService")
public class IpConnectionServiceImpl implements IpConnectionService {
    @Autowired
    private IpConnectionMapper ipConnectionMapper;

    Properties properties = new PropertiesHandle().handleProperty("properties/config.properties");
    private long expiretime = Long.parseLong(properties.getProperty("expiretime"));

    @Override
    public int queryIpConnection(String ip) {
        IpConnectionExample ipConnectionExample = new IpConnectionExample();
        IpConnectionExample.Criteria criteria = ipConnectionExample.createCriteria();
        long ctime = System.currentTimeMillis() - expiretime;
        Timestamp timestamp = new Timestamp(ctime);
        criteria.andIpEqualTo(ip).andLastreqtimeGreaterThan(timestamp);
        return ipConnectionMapper.selectByExample(ipConnectionExample).size();
    }

    @Override
    public void updateIpConnection(String ip, String macadress) {
        IpConnectionExample ipConnectionExample = new IpConnectionExample();
        IpConnection ipConnection = new IpConnection();
        ipConnection.setIp(ip);
        ipConnection.setMacadress(macadress);
        ipConnection.setLastreqtime(new Timestamp(System.currentTimeMillis()));
        IpConnectionExample.Criteria criteria = ipConnectionExample.createCriteria();
        criteria.andIpEqualTo(ip).andMacadressEqualTo(macadress);
        int i = ipConnectionMapper.updateByExampleSelective(ipConnection, ipConnectionExample);
        if (i==0){
            insertIpConnection(ip,macadress);
        }
    }

    @Override
    public void insertIpConnection(String ip, String macadress) {
        IpConnection ipConnection = new IpConnection();
        ipConnection.setIp(ip);
        ipConnection.setMacadress(macadress);
        ipConnectionMapper.insert(ipConnection);
    }

    @Override
    public void cleantable() {
        long ctime = System.currentTimeMillis() - expiretime;
        Timestamp timestamp = new Timestamp(ctime);
        ipConnectionMapper.deleteExpire(timestamp.toString());
    }
}
