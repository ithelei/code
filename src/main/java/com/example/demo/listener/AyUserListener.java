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
     * springboot集成redis
     *
     * AyUserListener监听器类，
     * 并在上下文启动时候打印信息。在这节当中，
     * 我们想在上下文初始化的方法中，
     * 加载数据库中的所有用户数据，并存放到Redis缓存中。
     * 之所以要把用户数据存放到缓存中，是因为用户的数据属于变动不大的数据，
     * 适合存放到缓存中，在应用需要获取用户数据时，可以直接到Redis缓存中获取，
     * 不用到数据库中获取数据库连接查询数据，提高数据的访问速度
     */

//    @Resource
//    private RedisTemplate redisTemplate;
//    @Resource
//    private AyUserService ayUserService;
//    private static final String ALL_USER = "ALL_USER_LIST";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        //查询所有的用户
//        List<AyUser> ayUserList= ayUserService.findAll();
//        //清除缓存中的用户数据
//
//        redisTemplate.delete(ALL_USER);
//        //将数据存放到redis缓存中
//        redisTemplate.opsForList().leftPushAll(ALL_USER, ayUserList);
//
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


/**
 * redisTemplate.opsForList().leftPushAll：查询缓存中所有的用户数据， ALL_USER键若不存在，会创建该键及与其关联的List，之后再将参数中的ayUserList从左到右依次插入。
 * redisTemplate.opsForList().range：取链表中的全部元素，其中0表示第一个元素，-1表示最后一个元素。
 * 我们已经提到，当我们的数据存放到Redis的时候，键（key）和值（value）都是通过Spring提供的Serializer序列化到数据库的。RedisTemplate默认使用JdkSerializationRedisSerializer，
 * 而StringRedisTemplate默认使用StringRedisSerializer。所有我们需要让用户类AyUser（/src/main/java/com.example.demo.model）实现序列化接口Serializable，具体代码如下：
 *
 */
