package com.unionpay.taskmonitor.service.dao;

import com.unionpay.taskmonitor.service.model.SpiderStatus;
import com.unionpay.taskmonitor.service.model.SpiderStatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SpiderStatusMapper {
    int insert(SpiderStatus record);

    int insertSelective(SpiderStatus record);

    List<SpiderStatus> selectByExample(SpiderStatusExample example);

    int updateByExampleSelective(@Param("record") SpiderStatus record, @Param("example") SpiderStatusExample example);

    int updateByExample(@Param("record") SpiderStatus record, @Param("example") SpiderStatusExample example);
}