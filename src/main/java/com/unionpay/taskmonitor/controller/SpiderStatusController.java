package com.unionpay.taskmonitor.controller;

import com.unionpay.taskmonitor.service.model.SpiderStatus;
import com.unionpay.taskmonitor.service.service.SpiderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by luffylg on 2017/9/1.
 */
@Controller
public class SpiderStatusController {
    @Autowired
    private SpiderStatusService spiderStatusService;

    @RequestMapping(value = {"/spiderstatus"},method = {RequestMethod.GET})
    @ResponseBody
    public SpiderStatus spiderstatus(HttpServletRequest httpServletRequest) {
        SpiderStatus status=new SpiderStatus();
        List all = spiderStatusService.getAll();
        if (!all.isEmpty()){
            status = (SpiderStatus) all.get(0);
        }
        return status;
    }
}
