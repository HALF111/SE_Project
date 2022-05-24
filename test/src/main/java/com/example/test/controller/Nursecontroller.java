package com.example.test.controller;

import com.example.test.entity.Nurse;
import com.example.test.entity.Result;
import com.example.test.repository.Nurserepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nurseuser")
public class Nursecontroller {
    @Autowired
    private Nurserepository nurserepository;

    @PostMapping("/save")
    public Result save(@RequestBody Result<Nurse> nurse) {
        Nurse search = nurserepository.findByLoginname(nurse.getResult().getLoginname());//先查看账号是否重复
        Result result = new Result<>();
        result.setSuccess(0);
        if (search != null) {
            result.setSrc("error,account exist");
            return result;
        }
        Nurse result1 = nurserepository.save(nurse.getResult());//存入
        if (result1.equals(nurse.getResult())) {
            result.setSuccess(1);
        } else {
            result.setSrc("error,save error");
        }
        return result;
    }//将数据存入user数据库，url为/user/save，存入成功则返回“success”，否则“error”

    @PostMapping("/login")
    public Result login(@RequestBody Result<Nurse> nurse) {
        Nurse result = nurserepository.findByLoginname(nurse.getResult().getLoginname());
        Result result1 = new Result<>();
        if (result != null && result.getPasswd().equals(nurse.getResult().getPasswd())) {
            result1.setSuccess(1);
        } else {
            result1.setSuccess(0);
            if (result == null)
                result1.setSrc("error,loginname not exist");
            else
                result1.setSrc("error,wrong passwd");
        }
        return result1;
    }//查询user数据库，看输入的user实例是否可以匹配一项，是则返回“success”，否则“error”

    @PostMapping("/update")
    public Result update(@RequestBody Result<Nurse> nurse) {
        Nurse search = nurserepository.findByLoginname(nurse.getResult().getLoginname());//先查看账号是否重复
        Result result = new Result();
        result.setSuccess(0);
        if (search == null) {
            result.setSrc("error,account not exist");
            return result;
        }
        Nurse rr = nurserepository.save(nurse.getResult());//存入
        if (rr.equals(nurse.getResult())) {
            result.setSuccess(1);
        } else {
            result.setSrc("error,update error");

        }
        return result;
    }//将数据存入user数据库，url为/user/save，存入成功则返回“success”，否则“error”

    @Transactional
    @PostMapping("/remove")
    public Result remove(@RequestBody Integer id) {
        Nurse p = nurserepository.findById(id).orElse(null);
        int i = 0;
        Result result = new Result();
        if (p == null) {
            result.setSuccess(i);
            return result;
        }
        i = nurserepository.removeById(id);
        result.setSuccess(i);
        return result;
    }

    @GetMapping("searchid")
    public Result<Nurse> searchid(Integer id) {
        Nurse p = nurserepository.findById(id).orElse(null);
        Result<Nurse> result = new Result<>();
        if (p == null) {
            result.setSuccess(0);
        } else {
            result.setSuccess(1);
            result.setResult(p);
        }
        return result;
    }

    @GetMapping("searchloginname")
    public Result<Nurse> searchloginname(String loginname) {
        Nurse p = nurserepository.findByLoginname(loginname);
        Result<Nurse> result = new Result<>();
        if (p == null) {
            result.setSuccess(0);
        } else {
            result.setSuccess(1);
            result.setResult(p);
        }
        return result;
    }

}
