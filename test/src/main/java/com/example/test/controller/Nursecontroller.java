package com.example.test.controller;

import com.example.test.entity.Nurse;
import com.example.test.repository.Nurserepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nurseuser")
public class Nursecontroller {
    @Autowired
    private Nurserepository nurserepository;

    @PostMapping("/save")
    public String save(@RequestBody Nurse nurse) {
        Nurse search = nurserepository.findByLoginname(nurse.getLoginname());//先查看账号是否重复
        if (search != null) {
            return "error,account exist";
        }
        Nurse result = nurserepository.save(nurse);//存入
        if (result.equals(nurse)) {
            return "success";
        } else {
            return "error,save error";
        }
    }//将数据存入user数据库，url为/user/save，存入成功则返回“success”，否则“error”

    @PostMapping("/login")
    public String login(@RequestBody Nurse patientuser) {
        Nurse result = nurserepository.findByLoginname(patientuser.getLoginname());
        if (result != null && result.getPasswd().equals(patientuser.getPasswd())) {
            return "success";
        } else {
            return "error";
        }
    }//查询user数据库，看输入的user实例是否可以匹配一项，是则返回“success”，否则“error”
}
