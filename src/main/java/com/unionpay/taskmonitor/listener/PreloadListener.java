package com.unionpay.taskmonitor.listener;

import com.unionpay.taskmonitor.clocktask.NodeCleaning;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by luffylg on 2017/7/3.
 */
public class PreloadListener implements ServletContextListener {
    NodeCleaning nodeCleaning = new NodeCleaning();
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        nodeCleaning.cleanExpireNode();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        nodeCleaning.cleanthread();
    }
}
