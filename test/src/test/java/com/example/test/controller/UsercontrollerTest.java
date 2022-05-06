package com.example.test.controller;

import com.example.test.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UsercontrollerTest {
    @Autowired
    Usercontroller usercontroller;

    @Test
    void save() {
        User u = new User();
        u.setAccount("5555");
        u.setPassword("5555");
        System.out.println(usercontroller.save(u));
    }

    @Test
    void login() {
        User u1 = new User();
        u1.setAccount("1111");
        u1.setPassword("1111");
        User u2 = new User();
        u2.setAccount("2");
        u2.setPassword("2");
        System.out.println(usercontroller.login(u1));
        System.out.println(usercontroller.login(u2));
    }
}