package com.example.test.repository;

import com.example.test.entity.Doctoruser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Doctoruserrepository extends JpaRepository<Doctoruser, Integer> {
    public Doctoruser findByAccount(String account);//用账号查询数据库，返回一个user

    public Doctoruser[] findByPassword(String password);//用密码查询数据库，返回user数组
}
