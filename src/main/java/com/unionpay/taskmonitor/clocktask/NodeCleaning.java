package com.unionpay.taskmonitor.clocktask;

import com.unionpay.taskmonitor.dao.IpConnectionDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    //隔一段时间定期清理过期的节点
    public void cleanExpireNode(){
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    IpConnectionDao ipConnectionDao = new IpConnectionDao();
                    LOGGER.info("cleaning expiring nodes");
                    ipConnectionDao.cleantable();
                    ipConnectionDao.close();
                } catch (Exception e){
                    LOGGER.error(e.getMessage());
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
