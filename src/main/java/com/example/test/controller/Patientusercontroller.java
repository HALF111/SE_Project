package com.example.test.controller;

import com.example.test.entity.Patientuser;
import com.example.test.repository.Patientuserrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patientuser")
public class Patientusercontroller {
    @Autowired
    private Patientuserrepository patientuserrepository;

    @PostMapping("/save")
    public String save(@RequestBody Patientuser patientuser) {
        Patientuser search = patientuserrepository.findByAccount(patientuser.getAccount());//先查看账号是否重复
        if (search != null) {
            return "error,account exist";
        }
        Patientuser result = patientuserrepository.save(patientuser);//存入
        if (result.equals(patientuser)) {
            return "success";
        } else {
            return "error,save error";
        }
    }//将数据存入user数据库，url为/user/save，存入成功则返回“success”，否则“error”

    @PostMapping("/login")
    public String login(@RequestBody Patientuser patientuser) {
        Patientuser result = patientuserrepository.findByAccount(patientuser.getAccount());
        if (result != null && result.getPassword().equals(patientuser.getPassword())) {
            return "success";
        } else {
            return "error";
        }
    }//查询user数据库，看输入的user实例是否可以匹配一项，是则返回“success”，否则“error”
}
