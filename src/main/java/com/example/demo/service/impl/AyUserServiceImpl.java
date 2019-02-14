package com.example.demo.service.impl;

import com.example.demo.dao.AyUserDao;
import com.example.demo.model.AyUser;
import com.example.demo.repository.AyUserRespository;
import com.example.demo.service.AyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

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
        return   ayUserRespository.findAll();
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


}
