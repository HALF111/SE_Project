package com.example.test.repository;

import com.example.test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Userrepository extends JpaRepository<User, Integer> {
    User findByAccount(String account);//用账号查询数据库，返回一个user
    
    User[] findByPassword(String password);//用密码查询数据库，返回user数组
}
