package com.example.test.controller;

import com.example.test.entity.Doctoruser;
import com.example.test.entity.Result;
import com.example.test.repository.Doctoruserrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctoruser")
public class Doctorusercontroller {
    @Autowired
    private Doctoruserrepository doctoruserrepository;

    @PostMapping("/save")
    public String save(@RequestBody Result<Doctoruser> doctoruser) {
        Doctoruser search = doctoruserrepository.findByAccount(doctoruser.getResult().getAccount());//先查看账号是否重复
        if (search != null) {
            return "error,account exist";
        }
        Doctoruser result = doctoruserrepository.save(doctoruser.getResult());//存入
        if (result.equals(doctoruser.getResult())) {
            return "success";
        } else {
            return "error,save error";
        }
    }//将数据存入user数据库，url为/user/save，存入成功则返回“success”，否则“error”

    @PostMapping("/login")
    public String login(@RequestBody Result<Doctoruser> doctoruser) {
        Doctoruser result = doctoruserrepository.findByAccount(doctoruser.getResult().getAccount());
        if (result != null && result.getPassword().equals(doctoruser.getResult().getPassword())) {
            return "success";
        } else {
            return "error";
        }
    }//查询user数据库，看输入的user实例是否可以匹配一项，是则返回“success”，否则“error”
}
