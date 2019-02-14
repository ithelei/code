package com.example.demo.dao;

import com.example.demo.model.AyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * spring集成mybatis
 * 用户dao
 */
@Mapper
public interface AyUserDao {

    /**
     *  描述：通过用户名和密码查询用户
     * @param name
     * @param password
     */

    AyUser findByNameAndPassword(@Param("name") String name, @Param("password") String password);

}
