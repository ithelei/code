package com.example.demo.quartz;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时器类
 */

@Component
@Configurable
@EnableScheduling
public class SendMailQuartz {

    //日志对象
    private static final Logger logger = LogManager.getLogger(SendMailQuartz.class);


    //每5秒执行一次
    @Scheduled(cron = "*/5 * *  * * * ")
    public void reportCurrentByCron(){
        logger.info("定时器运行了!!!");
    }



}
