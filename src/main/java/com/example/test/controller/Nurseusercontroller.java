package com.example.test.controller;

import com.example.test.entity.Nurseuser;
import com.example.test.repository.Nurseuserrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nurseuser")
public class Nurseusercontroller {
    @Autowired
    private Nurseuserrepository nurseuserrepository;

    @PostMapping("/save")
    public String save(@RequestBody Nurseuser nurseuser) {
        Nurseuser search = nurseuserrepository.findByAccount(nurseuser.getAccount());//先查看账号是否重复
        if (search != null) {
            return "error,account exist";
        }
        Nurseuser result = nurseuserrepository.save(nurseuser);//存入
        if (result.equals(nurseuser)) {
            return "success";
        } else {
            return "error,save error";
        }
    }//将数据存入user数据库，url为/user/save，存入成功则返回“success”，否则“error”

    @PostMapping("/login")
    public String login(@RequestBody Nurseuser patientuser) {
        Nurseuser result = nurseuserrepository.findByAccount(patientuser.getAccount());
        if (result != null && result.getPassword().equals(patientuser.getPassword())) {
            return "success";
        } else {
            return "error";
        }
    }//查询user数据库，看输入的user实例是否可以匹配一项，是则返回“success”，否则“error”
}
