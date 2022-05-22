package com.example.test.controller;

import com.example.test.entity.Patient;
import com.example.test.repository.Patientrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patientuser")
public class Patientcontroller {
    @Autowired
    private Patientrepository patientrepository;

    @PostMapping("/save")
    public String save(@RequestBody Patient patient) {
        Patient search = patientrepository.findByLoginname(patient.getLoginname());//先查看账号是否重复
        if (search != null) {
            return "error,account exist";
        }
        Patient result = patientrepository.save(patient);//存入
        if (result.equals(patient)) {
            return "success";
        } else {
            return "error,save error";
        }
    }//将数据存入user数据库，url为/user/save，存入成功则返回“success”，否则“error”

    @PostMapping("/update")
    public String update(@RequestBody Patient patient) {
        Patient search = patientrepository.findByLoginname(patient.getLoginname());//先查看账号是否重复
        if (search == null) {
            return "error,account not exist";
        }
        Patient result = patientrepository.save(patient);//存入
        if (result.equals(patient)) {
            return "success";
        } else {
            return "error,update error";
        }
    }//将数据存入user数据库，url为/user/save，存入成功则返回“success”，否则“error”

    @PostMapping("/login")
    public String login(@RequestBody Patient patient) {
        Patient result = patientrepository.findByLoginname(patient.getLoginname());
        if (result != null && result.getPasswd().equals(patient.getPasswd())) {
            return "success";
        } else {
            return "error";
        }
    }//查询user数据库，看输入的user实例是否可以匹配一项，是则返回“success”，否则“error”
}
