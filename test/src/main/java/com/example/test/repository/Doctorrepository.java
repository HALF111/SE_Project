package com.example.test.repository;

import com.example.test.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Doctorrepository extends JpaRepository<Doctor, Integer> {
    public Doctor findByLoginname(String loginName);//用账号查询数据库，返回一个user

    public List<Doctor> findByPasswd(String passwd);//用密码查询数据库，返回user数组

    public int removeById(Integer id);
}
