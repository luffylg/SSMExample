package com.unionpay.taskmonitor.service.service.impl;

import com.unionpay.taskmonitor.service.dao.SpiderStatusMapper;
import com.unionpay.taskmonitor.service.model.SpiderStatusExample;
import com.unionpay.taskmonitor.service.service.SpiderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luffylg on 2017/8/31.
 */
@Service("spiderStatusService")
public class SpiderStatusServiceImpl implements SpiderStatusService {
    @Autowired
    private SpiderStatusMapper spiderStatusMapper;
    @Override
    public List getAll() {
        SpiderStatusExample spiderStatusExample = new SpiderStatusExample();
        SpiderStatusExample.Criteria criteria = spiderStatusExample.createCriteria();
        criteria.andIsaliveIsNotNull();
        return spiderStatusMapper.selectByExample(spiderStatusExample);
    }
}
