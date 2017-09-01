package com.unionpay.taskmonitor.clocktask;

import com.unionpay.taskmonitor.dao.IpConnectionDao;
import com.unionpay.taskmonitor.service.service.IpConnectionService;
import com.unionpay.taskmonitor.service.service.impl.IpConnectionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by luffylg on 2017/6/29.
 */
public class NodeCleaning {
    public static final Logger LOGGER = LoggerFactory.getLogger(NodeCleaning.class);
    public ScheduledExecutorService service = Executors
            .newSingleThreadScheduledExecutor();

    private IpConnectionService ipConnectionService=new IpConnectionServiceImpl();

    //隔一段时间定期清理过期的节点
    public void cleanExpireNode(){
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    LOGGER.info("cleaning expiring nodes");
                    ipConnectionService.cleantable();
                } catch (Exception e){
                    LOGGER.error(e.toString());
                }
            }
        };

        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(runnable, 0, 12, TimeUnit.HOURS);
    }
    public void cleanthread(){
        service.shutdown();
    }
}
