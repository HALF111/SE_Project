package com.example.test.controller;

import com.example.test.entity.User;
import com.example.test.repository.Userrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class Usercontroller {
    @Autowired
    private Userrepository userrepository;

    @PostMapping("/save")
    public String save(@RequestBody User user) {
        User search = userrepository.findByAccount(user.getAccount());//先查看账号是否重复
        if (search != null) {
            return "error,account exist";
        }
        User result = userrepository.save(user);//存入
        if (result.equals(user)) {
            return "success";
        } else {
            return "error,save error";
        }
    }//将数据存入user数据库，url为/user/save，存入成功则返回“success”，否则“error”

    public String login(@RequestBody User user) {
        User result = userrepository.findByAccount(user.getAccount());
        if (result != null && result.getPassword().equals(user.getPassword())) {
            return "success";
        } else {
            return "error";
        }
    }//查询user数据库，看输入的user实例是否可以匹配一项，是则返回“success”，否则“error”
}
