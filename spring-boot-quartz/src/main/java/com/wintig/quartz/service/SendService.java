package com.wintig.quartz.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shitian
 * @create 2018-07-22 下午11:58
 */
@Service
public class SendService {

    public void send() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        System.out.println("发送了一条消息 ："+dateFormat.format(new Date()) );
    }

}
