package com.unionpay.taskmonitor.controller;

import com.unionpay.taskmonitor.service.service.IpConnectionService;
import com.unionpay.taskmonitor.service.service.SpiderStatusService;
import com.unionpay.taskmonitor.servlet.FrequencyServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by luffylg on 2017/9/1.
 */
@Controller
public class FrequencyController {
    @Autowired
    private IpConnectionService ipConnectionService;
    private static final Logger LOGGER = LoggerFactory.getLogger(FrequencyServlet.class);


    @RequestMapping(value = { "/frequency" }, method = RequestMethod.GET)
    @ResponseBody
    public Map frequency(HttpServletRequest httpServletRequest) {
        String ip = httpServletRequest.getParameter("ip");
        if (ip == null || Objects.equals(ip, "")) {
            ip = getRemoteAddress(httpServletRequest);
        }
        String macadress = httpServletRequest.getParameter("macadress");
        HashMap<String, Integer> map = new HashMap<>();
        if (macadress == null || Objects.equals(macadress, "")){
            map.put("error",0);
        }else {
            LOGGER.info(String.format("ip: %s macadress: %s frequency request", ip,macadress));
            // 以字段ip与macadress有则更新记录，无则插入。返回字段IP查询数目，查询时注意时间要小于过期时间
            ipConnectionService.updateIpConnection(ip,macadress);
            int connections = ipConnectionService.queryIpConnection(ip);
            map.put("nums",connections);
        }
        return map;
    }

    private static String getRemoteAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
