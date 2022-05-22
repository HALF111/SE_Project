package com.example.test.controller;

import com.example.test.entity.Nurse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NursecontrollerTest {
    @Autowired
    Nursecontroller nursecontroller;

    @Test
    void save() {
        Nurse n = new Nurse();
        n.setLoginname("1111");
        n.setPasswd("1111");
        System.out.println(nursecontroller.save(n));
    }

    @Test
    void login() {
    }
}