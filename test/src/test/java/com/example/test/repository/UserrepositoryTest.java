package com.example.test.repository;

import com.example.test.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserrepositoryTest {
    @Autowired
    private Userrepository userrepository;

    @Test
    void findall() {
        System.out.println(userrepository.findAll());

    }

    @Test
    void save() {
        User user = new User();
        user.setAccount("4444");
        user.setPassword("4444");
        User user1 = userrepository.save(user);
        System.out.println(user1);
    }

    @Test
    void findByAccount() {
        User result = userrepository.findByAccount("11111");
        if (result != null) {
            System.out.println(result);
        } else {
            System.out.println("error");
        }
    }

    @Test
    void findByPassword() {
        User[] result = userrepository.findByPassword("1111");
        if (result != null) {
            for (User u : result) {
                System.out.println(u);
            }
        } else {
            System.out.println("error");
        }
    }
}