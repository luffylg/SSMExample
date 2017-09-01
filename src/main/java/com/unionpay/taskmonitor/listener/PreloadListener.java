package com.unionpay.taskmonitor.listener;

import com.unionpay.taskmonitor.clocktask.NodeCleaning;
import com.unionpay.taskmonitor.service.service.IpConnectionService;
import com.unionpay.taskmonitor.service.service.impl.IpConnectionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by luffylg on 2017/7/3.
 */
public class PreloadListener implements ServletContextListener {
    public static final Logger LOGGER = LoggerFactory.getLogger(NodeCleaning.class);
    public ScheduledExecutorService service = Executors
            .newSingleThreadScheduledExecutor();
    private IpConnectionService ipConnectionService;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ipConnectionService = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext()).getBean(IpConnectionService.class);
        cleanExpireNode();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        cleanthread();
    }

    //隔一段时间定期清理过期的节点
    public void cleanExpireNode(){
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    LOGGER.info("cleaning expiring node");
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
