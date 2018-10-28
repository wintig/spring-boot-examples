package com.wintig.quartz.job;


import com.wintig.quartz.service.SendService;
import com.wintig.quartz.utils.DateUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalTime;


/**
 * @author shitian
 * @create 2018-07-22 下午11:57
 */
@Component
public class QuartzJob implements Job, Serializable {

    @Autowired
    public SendService sendService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        sendService.send();
    }


    public static Trigger buildTrigger() {

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("Send_Audit", "Send_Group")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(20).withIntervalInSeconds(1))
                .startAt(DateUtils.localTime2date(LocalTime.now().plusSeconds(2)))  //延迟2秒发送
                .build();

        return trigger;

    }


}