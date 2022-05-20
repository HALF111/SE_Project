package com.example.test.repository;

import com.example.test.entity.Nurseuser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface Nurseuserrepository extends JpaRepository<Nurseuser, Integer> {
    public Nurseuser findByAccount(String account);//用账号查询数据库，返回一个user

    public Nurseuser[] findByPassword(String password);//用密码查询数据库，返回user数组
}
