package com.unionpay.taskmonitor.servlet;

import com.alibaba.fastjson.JSON;
import com.unionpay.taskmonitor.bean.SpiderStatus;
import com.unionpay.taskmonitor.dao.SpiderStatusDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by luffylg on 2017/6/29.
 */
public class SpiderStatusServlet extends HttpServlet{
    private static final Logger LOGGER = LoggerFactory.getLogger(SpiderStatusServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter outer=resp.getWriter();
        try {
            LOGGER.info("getting spider status...");
            //查询数据库，返回爬虫状态
            SpiderStatusDao statusDao = new SpiderStatusDao();
            SpiderStatus spiderStatus = statusDao.QuerySpiderStatus();
            statusDao.close();
            if (spiderStatus == null){
                spiderStatus = new SpiderStatus();
                LOGGER.warn("no spiderstatus");
            }
            String data = JSON.toJSONString(spiderStatus);
            outer.write(data);
        } catch (Exception e) {
            outer.write("error");
            LOGGER.error(e.getMessage());
        } finally {
            outer.flush();
            outer.close();
        }
    }
}
