package com.example.test.controller;

import com.example.test.entity.Patient;
import com.example.test.entity.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PatientcontrollerTest {
    @Autowired
    Patientcontroller patientcontroller;

    @Test
    void save() {
        Patient p = new Patient();
        p.setLoginname("11111");
        p.setAge(18);
        p.setGender(1);
        p.setRealname("gg");
        p.setPasswd("1111");
        p.setId(1);
        Result<Patient> patientResult = new Result<>();
        patientResult.setResult(p);
        System.out.println(patientcontroller.save(patientResult));

    }

    @Test
    void update() {
        Patient p = new Patient();
        p.setId(1);
        p.setLoginname("1111");
        p.setAge(18);
        p.setGender(0);
        p.setRealname("ji");
        p.setPasswd("1111");
        Result<Patient> patientResult = new Result<>();
        patientResult.setResult(p);
        System.out.println(patientcontroller.update(patientResult));
    }

    @Test
    void login() {
        Patient p = new Patient();
        p.setLoginname("1111");
        p.setPasswd("1111");
        Result<Patient> patientResult = new Result<>();
        patientResult.setResult(p);
        System.out.println(patientcontroller.login(patientResult));
    }

    @Test
    void remove() {
        Result r = patientcontroller.remove(3);
        System.out.println(r.getSuccess());
    }
}