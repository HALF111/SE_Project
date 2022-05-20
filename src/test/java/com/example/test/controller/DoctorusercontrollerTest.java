package com.example.test.controller;

import com.example.test.entity.Doctoruser;
import com.example.test.entity.Result;
import com.example.test.repository.Doctoruserrepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DoctorusercontrollerTest {
    @Autowired
    Doctoruserrepository doctoruserrepository;

    @Test
    void save() {
        Doctoruser d = new Doctoruser();
        d.setAccount("1111");
        d.setPassword("1111");
        Result<Doctoruser> dd = new Result<Doctoruser>();
        dd.setSuccess(1);
        dd.setResult(d);
        System.out.println(doctoruserrepository.save(d));
    }

    @Test
    void login() {
    }
}