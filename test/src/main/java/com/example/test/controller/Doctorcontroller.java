package com.example.test.controller;

import com.example.test.entity.Doctor;
import com.example.test.entity.Result;
import com.example.test.repository.Doctorrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctoruser")
public class Doctorcontroller {
    @Autowired
    private Doctorrepository doctorrepository;

    @PostMapping("/save")
    public String save(@RequestBody Result<Doctor> doctoruser) {
        Doctor search = doctorrepository.findByLoginname(doctoruser.getResult().getLoginname());//先查看账号是否重复
        if (search != null) {
            return "error,account exist";
        }
        Doctor result = doctorrepository.save(doctoruser.getResult());//存入
        if (result.equals(doctoruser.getResult())) {
            return "success";
        } else {
            return "error,save error";
        }
    }//将数据存入user数据库，url为/user/save，存入成功则返回“success”，否则“error”

    @PostMapping("/login")
    public String login(@RequestBody Result<Doctor> doctoruser) {
        Doctor result = doctorrepository.findByLoginname(doctoruser.getResult().getLoginname());
        if (result != null && result.getPasswd().equals(doctoruser.getResult().getPasswd())) {
            return "success";
        } else {
            return "error";
        }
    }//查询user数据库，看输入的user实例是否可以匹配一项，是则返回“success”，否则“error”
}
