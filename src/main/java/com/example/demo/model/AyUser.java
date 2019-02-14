package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 用户表
 */

@Entity
@Table(name = "ay_user")
//序列化接口
public class AyUser{

    //主键
    @Id
    private  String id;

    //用户名
    private  String name;
    //密码
    private  String password;


    //无参构造器
    public AyUser() {

    }


    public AyUser(String id) {
        this.id = id;


    }


    public AyUser(String id, String name) {
        this.id = id;
        this.name = name;

    }
    //构造器
    public AyUser(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AyUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
