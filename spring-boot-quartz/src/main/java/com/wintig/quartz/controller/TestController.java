package com.wintig.quartz.controller;


import com.wintig.quartz.job.QuartzJob;
import com.wintig.quartz.service.QuartzManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shitian
 * @create 2018-07-23 上午12:00
 */
@RestController
public class TestController {

    @Autowired
    private QuartzManager quartzManager;

    @RequestMapping("/add")
    public Object add() {
        try {
            String jobName = "job1";
            String jobGroupName = "job1";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            System.out.println("TestQuartJob 开始启动 》》》》》："+dateFormat.format(new Date()));
            quartzManager.addJob(QuartzJob.class, QuartzJob.buildTrigger(), jobName, jobGroupName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @RequestMapping("/del")
    public Object del() {
        try {
            String jobName="job1";
            String jobGroupName="job1";
            quartzManager.deleteJob(jobName, jobGroupName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

}
