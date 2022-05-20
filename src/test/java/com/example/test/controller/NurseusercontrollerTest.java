package com.example.test.controller;

import com.example.test.entity.Nurseuser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NurseusercontrollerTest {
    @Autowired
    Nurseusercontroller nurseusercontroller;

    @Test
    void save() {
        Nurseuser n = new Nurseuser();
        n.setAccount("1111");
        n.setPassword("1111");
        System.out.println(nurseusercontroller.save(n));
    }

    @Test
    void login() {
    }
}