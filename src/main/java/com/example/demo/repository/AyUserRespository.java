package com.example.demo.repository;

import com.example.demo.model.AyUser;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.List;


/**
 * 集成spring data jpa
 */

/**
 * 与此同时，我们要在实体上添加@Entity,   @Table(name = "ay_user"), @Id
 */
public interface  AyUserRespository  extends JpaRepository<AyUser,String> {

    /**
     * 自定义查询方法
     */


    /**
     * 通过名字相等查询，参数为name
     * 相当于  select  id ,name,password from ay_user  where name =?1
     */


     List<AyUser> findByName(String name);

    /**
     * 通过名字like查询，参数为name
     * 相当于  select  id ,name,password  from ay_user  where name like ?1
     */

    List<AyUser> findByNameLike(String name);


    /**
     * 通过主键集合查询，参数id为集合。
     *   select id ,name,password from ay_user  where id in (?,?,?)
     *
     */

    List<AyUser> findByIdIn(Collection<String> ids) ;


}
