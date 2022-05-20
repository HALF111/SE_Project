package com.example.test.repository;

import com.example.test.entity.Patientuser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Patientuserrepository extends JpaRepository<Patientuser, Integer> {
    public Patientuser findByAccount(String account);//用账号查询数据库，返回一个user

    public Patientuser[] findByPassword(String password);//用密码查询数据库，返回user数组
}
