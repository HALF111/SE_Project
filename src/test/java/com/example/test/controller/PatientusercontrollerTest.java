package com.example.test.controller;

import com.example.test.entity.Patientuser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PatientusercontrollerTest {
    @Autowired
    Patientusercontroller patientusercontroller;

    @Test
    void save() {
        Patientuser p = new Patientuser();
        p.setAccount("1111");
        p.setPassword("1111");
        System.out.println(patientusercontroller.save(p));

    }

    @Test
    void login() {
        Patientuser p = new Patientuser();
        p.setAccount("1111");
        p.setPassword("1111");
        System.out.println(patientusercontroller.login(p));
    }
}