package com.example.demo.service.impl;

import com.example.demo.dao.AyUserDao;
import com.example.demo.error.BusinessException;
import com.example.demo.model.AyUser;
import com.example.demo.repository.AyUserRespository;
import com.example.demo.service.AyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

/**
 * 服务层实现类
 */

/**
 *  Sprinboot 事物支持。
 *  类级别事物
 */



@Transactional
@Service
public class AyUserServiceImpl implements AyUserService {

    Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    @Resource(name="ayUserRespository")
    private AyUserRespository ayUserRespository;

    @Override
    public AyUser findById (String id){
        return ayUserRespository.findById(id).orElse(null);
    }


    @Override
    public List<AyUser> findAll(){
       // return  ayUserRespository.findById(id).get();
       try {
        System.out.println("开始做任务");
        long start=System.currentTimeMillis();
        List<AyUser>  ayUserList=  ayUserRespository.findAll();
        long end=System.currentTimeMillis();
        System.out.println("完成任务："+(end-start)+"毫秒");

        return  ayUserList;
       }catch (Exception e){
           logger.error("method [findAll] error",e);
           return Collections.EMPTY_LIST;
       }


    }

       @Override
       @Transactional

       /**
        * spring中的方法事物支持
        */
    public AyUser save(AyUser ayUser){
       // return  ayUserRespository.save(ayUser);
           AyUser saveUser = ayUserRespository.save(ayUser);
           //出现空指针
           String error =null;
           error.split("/");
           return  saveUser;

    }

    @Override
    public void delete(String id){

          ayUserRespository.deleteById(id);

        logger.info("userId:" + id + "用户被删除");
    }

    /**
     * 分页
     * @param pageable
     * @return
     */

    @Override
    public Page<AyUser> findAll(Pageable pageable) {
        return ayUserRespository.findAll(pageable);
    }


    /**
     *
     * ############ 以下是我自己定义的方法  ############################
     *
     */

    @Override
    public List<AyUser> findByName (String name){
        return ayUserRespository.findByName(name);

    }


    @Override
    public List<AyUser> findByNameLike (String name){
        return ayUserRespository.findByNameLike(name);

    }


    @Override
    public List<AyUser> findByIdIn (Collection<String> ids){
        return ayUserRespository.findByIdIn(ids);

    }


    /**
     * mybatis
     */

    @Resource
    private AyUserDao ayUserDao;

    @Override
    public AyUser  findByNameAndPassword(String name,String password){

        return  ayUserDao.findByNameAndPassword(name,password);

    }


    //异步查询
    @Override
    @Async
    public Future<List<AyUser>> findAsynAll() {
        // return  ayUserRespository.findById(id).get();
        try {
            System.out.println("开始做任务");
            long start = System.currentTimeMillis();
            List<AyUser> ayUserList = ayUserRespository.findAll();
            long end = System.currentTimeMillis();
            System.out.println("完成任务：" + (end - start) + "毫秒");

            return new AsyncResult<List<AyUser>>(ayUserList);
        } catch (Exception e) {
            logger.error("method [findAll] error", e);
            return new AsyncResult<List<AyUser>>(null);
        }

    }

    /**
     *重试机制
     */
    @Override
    @Retryable(value= {BusinessException.class},maxAttempts = 5,backoff = @Backoff(delay = 5000,multiplier = 2))
    public AyUser findByNameAndPasswordRetry(String name, String password) {
        System.out.println("[findByNameAndPasswordRetry] 方法失败重试了！");
        throw new BusinessException();
    }


    /**
     * 以下是更新缓存数据
     */

    //项目启动加载所有用户数据到缓存之后，我们需要修改AyUserServiceImpl中的接口，比如
    //findById、save、delete等方法。因为如果在Redis缓存中查询不到数据，
    // 我们需要到数据库查询，如果能够在数据库中查询到数据，除了返回数据之外，还需要把数据更新到缓存中。
    // 这样下次再次查询数据时，就不需要到数据库中查询数据。
    // 这里主要对方法findById进行修改AyUserServiceImpl具体需要修改的代码如下：



  //  @Resource
    //private RedisTemplate redisTemplate;
    //private static final String ALL_USER = "ALL_USER_LIST";

    /**
     *  描述：通过用户名查询用户
     * @param name
     */
    @Override
    public AyUser findByUserName(String name) {
        return ayUserDao.findByUserName(name);
    }

}
