package com.example.demo.service;

import com.example.demo.model.AyUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * 服务层类实现
 *
 */
public interface AyUserService {

    /**
     * 查询单个数据
     * @param id
     * @return
     */
    AyUser findById (String id);

    /**
     * 查询所有数据
     * @return
     */
    List<AyUser> findAll();

    /**
     * 用来保存和更新数据的功能
     * @param ayUser
     * @return
     */
    AyUser save (AyUser ayUser);

    /**
     * 删除数据
     * @param id
     */
    void  delete (String id);

    /**
     * 分页
     */

    Page<AyUser> findAll(Pageable pageable);

    /**
     * 自定义的方法
     */

    List<AyUser> findByName (String name);

    List<AyUser> findByNameLike(String name);

    List<AyUser> findByIdIn(Collection<String> ids) ;


    /**
     * mybatis
     */

    AyUser  findByNameAndPassword(String name,String password);
}
