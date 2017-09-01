package com.unionpay.taskmonitor.servlet;

import com.alibaba.fastjson.JSON;
import com.unionpay.taskmonitor.dao.IpConnectionDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by luffylg on 2017/6/29.
 */
public class FrequencyServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(FrequencyServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        // 返回当前IP连接数
        PrintWriter outer = resp.getWriter();

        String ip = req.getParameter("ip");
        if (ip == null || Objects.equals(ip, "")){
//            ip = getRemoteAddress(req);
//            ip = req.getRemoteAddr();
        }
        String macadress = req.getParameter("macadress");

        try {
            if (macadress == null || Objects.equals(macadress, "")){
                outer.write("no macadress error");
            }else {
                LOGGER.info(String.format("ip: %s macadress: %s frequency request", ip,macadress));
                // 以字段ip与macadress有则更新记录，无则插入。返回字段IP查询数目，查询时注意时间要小于过期时间
                IpConnectionDao ipConnectionDao = new IpConnectionDao();
                ipConnectionDao.updateIpConnection(ip, macadress);
                int connections = ipConnectionDao.queryIpConnection(ip);
                ipConnectionDao.close();
                HashMap<String, Integer> map = new HashMap<>();
                map.put("nums",connections);
                outer.write(JSON.toJSONString(map));
            }
        } catch (Exception e) {
            outer.write("error");
            LOGGER.error(e.getMessage());
        } finally {
            outer.flush();
            outer.close();
        }
    }

//    public String getRemoteAddress(HttpServletRequest request) {
//        String ip = request.getHeader("x-forwarded-for");
//        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
//            ip = request.getRemoteAddr();
//        }
//        return ip;
//    }

}
