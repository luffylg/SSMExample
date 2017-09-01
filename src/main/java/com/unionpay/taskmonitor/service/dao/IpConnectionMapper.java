package com.unionpay.taskmonitor.service.dao;

import com.unionpay.taskmonitor.service.model.IpConnection;
import com.unionpay.taskmonitor.service.model.IpConnectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IpConnectionMapper {
    int insert(IpConnection record);

    int insertSelective(IpConnection record);

    List<IpConnection> selectByExample(IpConnectionExample example);

    int updateByExampleSelective(@Param("record") IpConnection record, @Param("example") IpConnectionExample example);

    int updateByExample(@Param("record") IpConnection record, @Param("example") IpConnectionExample example);

    void deleteExpire(String s);
}