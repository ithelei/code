package com.example.demo.service;


import com.example.demo.model.AyMood;

/**
 * 微信说说服务层
 */
public interface AyMoodService {

    AyMood save(AyMood ayMood);

    /**
     *生产者和消费者开发完成之后，现在我们把用户发说说改成异步消费模式。首先我们在AyMoodService类下添加异步保存接口asynSave()
     */
    String asynSave(AyMood ayMood);
}
