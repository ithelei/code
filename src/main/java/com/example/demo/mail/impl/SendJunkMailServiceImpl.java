package com.example.demo.mail.impl;

import com.example.demo.mail.SendJunkMailService;
import com.example.demo.model.AyUser;
import com.example.demo.service.AyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * 发送用户邮件服务
 */

@Service
public class SendJunkMailServiceImpl implements SendJunkMailService {

    @Autowired
    JavaMailSender mailSender;
    @Resource
    private AyUserService ayUserService;
    @Value("${spring.mail.username}")
    private String from;


    public static final Logger logger =
            LogManager.getLogger(SendJunkMailServiceImpl.class);


    @Override
    public boolean sendJunkMail(List<AyUser> ayUserList) {

        try{
            if(ayUserList == null || ayUserList.size() <= 0 )
            {
                return Boolean.FALSE;
            }
            for(AyUser ayUser: ayUserList){
                MimeMessage mimeMessage = this.mailSender.createMimeMessage();
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                //邮件发送方
                message.setFrom(from);
                //邮件主题
                message.setSubject("定时任务发送邮件给芳芳");
                //邮件接收方
                message.setTo("13269463236@163.com");
                //邮件内容
                message.setText("fangfang ,不论怎样删不删我,都是我一辈子心里最重要的人!!!,抹杀不掉了");
                //发送邮件
                this.mailSender.send(mimeMessage);
            }
        }catch(Exception ex){
            logger.error("sendJunkMail error and ayUser=%s", ayUserList, ex);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }



}
