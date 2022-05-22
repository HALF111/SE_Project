package com.example.test.controller;

import com.example.test.entity.Doctor;
import com.example.test.entity.Medicalrecord;
import com.example.test.entity.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class MedicalrecordcontrollerTest {
    @Autowired
    Medicalrecordcontroller medicalrecordcontroller;

    @Test
    void insertrecord() {
        Medicalrecord m = new Medicalrecord();
        Patient p = new Patient();
        p.setId(1);
        p.setLoginname("1111");
        p.setPasswd("1111");
        m.setPatientid(p);
        Doctor d = new Doctor();
        d.setId(1);
        d.setLoginname("1111");
        d.setPasswd("1111");
        m.setDoctorid(d);
        m.setRegistrationtime(new Date());
        m.setDiagnosiscontent("没救了");
        System.out.println(medicalrecordcontroller.insertrecord(m));
    }

    @Test
    void searchrecord() {
    }
}