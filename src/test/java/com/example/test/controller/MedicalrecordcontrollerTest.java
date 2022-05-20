package com.example.test.controller;

import com.example.test.entity.Medicalrecord;
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
        m.setPatientid(1);
        m.setDoctorid(1);
        m.setDday(new Date());
        m.setSrc("没救了");
        System.out.println(medicalrecordcontroller.insertrecord(m));
    }

    @Test
    void searchrecord() {
    }
}