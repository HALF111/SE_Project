package com.example.test.repository;

import com.example.test.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Patientrepository extends JpaRepository<Patient, Integer> {
    public Patient findByLoginname(String loginName);//用账号查询数据库，返回一个user

    public List<Patient> findByPasswd(String password);//用密码查询数据库，返回user数组

    public Patient removeById(Integer id);
}
