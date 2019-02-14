package com.example.demo.listener;

import com.example.demo.model.AyUser;
import com.example.demo.service.AyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * 描述：监听器
 *
 */
@WebListener
public class AyUserListener implements ServletContextListener {


    Logger logger = LogManager.getLogger(this.getClass());


    /**
     * redis缓存在springboot中使用
     */
//    @Resource
//    private RedisTemplate redisTemplate;
//    @Resource
//    private AyUserService ayUserService;
//
//    private static final String ALL_USER = "ALL_USER_LIST";



    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {//查询数据库所有的用户
//        List<AyUser> ayUserList =  ayUserService.findAll();
//        //清除缓存中的用户数据
//        redisTemplate.delete(ALL_USER);
//        //将数据存放到redis缓存中
//        redisTemplate.opsForList().leftPushAll(ALL_USER, ayUserList);
//        //真实项目中需要注释掉，查询所有的用户数据
//        List<AyUser> queryUserList = redisTemplate.opsForList().range(ALL_USER, 0, -1);
//        System.out.println("缓存中目前的用户数有：" + queryUserList.size() + " 人");

        System.out.println("ServletContext上下文初始化");
        logger.info("ServletContext上下文初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent)
    {
        System.out.println("ServletContext上下文销毁");

        logger.info("ServletContext上下文销毁");
    }

}


/**
 * @WebListener：用于将一个类声明为监听器，
 * 该注解将会在应用部署时被容器处理，容器根据具体的属性配置将相应的类部署为监听器。
 * 这样我们在Web应用中使用监听器时，不需要在web.xml文件中配置监听器的相关描述信息了。
ServletContextListener类：能够监听ServletContext对象的生命周期，
实际上就是监听Web应用的生命周期。当Servlet 容器启动或终止Web应用时，
会触发ServletContextEvent 事件，该事件由ServletContextListener类来处理。
在ServletContextListener接口中定义了处理ServletContextEvent 事件的两个方法：
contextInitialized和contextDestroyed。

contextInitialized：当Servlet容器启动Web 应用时调用该方法。在调用完该方法之后，容器再对Filter 初始化，并且对那些在Web 应用启动时就需要被初始化的Servlet 进行初始化。
contextDestroyed：当Servlet容器终止Web应用时调用该方法。在调用该方法之前，容器会先销毁所有的Servlet和Filter过滤器。
我们可以在contextInitialized方法中查询所有的用户，利用缓存技术把用户数据存放到缓存中。在第七章中我们会具体讲解如何利用监听器和Redis缓存技术来缓存用户数据，提高系统性能

 */
