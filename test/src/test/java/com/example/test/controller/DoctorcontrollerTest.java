package com.example.test.controller;

import com.example.test.entity.Doctor;
import com.example.test.entity.Result;
import com.example.test.repository.Doctorrepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DoctorcontrollerTest {
    @Autowired
    Doctorrepository doctorrepository;

    @Test
    void save() {
        Doctor d = new Doctor();
        d.setLoginname("1111");
        d.setPasswd("1111");
        
        Result<Doctor> dd = new Result<Doctor>();
        dd.setSuccess(1);
        dd.setResult(d);
        System.out.println(doctorrepository.save(d));
    }

    @Test
    void login() {
    }
}