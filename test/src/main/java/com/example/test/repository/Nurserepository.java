package com.example.test.repository;

import com.example.test.entity.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;


public interface Nurserepository extends JpaRepository<Nurse, Integer> {
    public Nurse findByLoginname(String loginName);//用账号查询数据库，返回一个user

    public Nurse[] findByPasswd(String passwd);//用密码查询数据库，返回user数组

    public int removeById(Integer id);
}
