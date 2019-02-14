package com.example.demo.quartz;


import com.example.demo.mail.SendJunkMailService;
import com.example.demo.model.AyUser;
import com.example.demo.service.AyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 发送邮件生产环境
 */

@Component
@Configurable
@EnableScheduling
public class SendMailQuartzSchj {

    //日志对象
    private static final Logger logger = LogManager.getLogger(SendMailQuartzSchj.class);



    @Resource
    private SendJunkMailService sendJunkMailService;
    @Resource
    private AyUserService ayUserService;


    //每5秒执行一次
   // @Scheduled(cron = "*/5 * *  * * * ")
    public void reportCurrentByCron(){
        List<AyUser> userList = ayUserService.findAll();
        if (userList == null || userList.size() <= 0)
        {
            return;
        }
        //发送邮件
        sendJunkMailService.sendJunkMail(userList);
        logger.info("我的邮件定时任务运行了，!!!");
    }



}
