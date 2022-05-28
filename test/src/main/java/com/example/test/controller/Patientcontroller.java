package com.example.test.controller;

import com.example.test.entity.Patient;
import com.example.test.entity.Result;
import com.example.test.repository.Patientrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patientuser")
public class Patientcontroller {
    @Autowired
    private Patientrepository patientrepository;
    @Autowired
    private Medicalrecordcontroller medicalrecordcontroller;

    @PostMapping("/save")
    public Result save(@RequestBody Result<Patient> patient) {
        Patient search = patientrepository.findByLoginname(patient.getResult().getLoginname());//先查看账号是否重复
        Result result = new Result();
        result.setSuccess(0);
        if (search != null) {
            result.setSrc("error,account exist");
            return result;
        }
        Patient rr = patientrepository.save(patient.getResult());//存入
        if (rr.equals(patient.getResult())) {
            result.setSuccess(1);
        } else {
            result.setSrc("error,save error");
        }
        return result;
    }//将数据存入user数据库，url为/user/save，存入成功则返回“success”，否则“error”

    @PostMapping("/update")
    public Result update(@RequestBody Result<Patient> patient) {
        Patient search = patientrepository.findByLoginname(patient.getResult().getLoginname());//先查看账号是否重复
        Result result = new Result();
        result.setSuccess(0);
        if (search == null) {
            result.setSrc("error,account not exist");
            return result;
        }
        Patient rr = patientrepository.save(patient.getResult());//存入
        if (rr.equals(patient.getResult())) {
            result.setSuccess(1);
        } else {
            result.setSrc("error,update error");

        }
        return result;
    }//将数据存入user数据库，url为/user/save，存入成功则返回“success”，否则“error”

    @PostMapping("/login")
    public Result login(@RequestBody Result<Patient> patient) {
        Patient result = patientrepository.findByLoginname(patient.getResult().getLoginname());
        Result result1 = new Result();
        if (result != null && result.getPasswd().equals(patient.getResult().getPasswd())) {
            result1.setSuccess(1);
        } else {
            if (result == null)
                result1.setSrc("error,loginname not exist");
            else
                result1.setSrc("error,wrong password");
            result1.setSuccess(0);
        }
        return result1;
    }//查询user数据库，看输入的user实例是否可以匹配一项，是则返回“success”，否则“error”

    public Patient findById(int id) {
        Patient patient = patientrepository.findById(id).orElse(null);
        return patient;
    }

    public Patient removeByid(int id) {
        return patientrepository.removeById(id);
    }

    @GetMapping("searchid")
    public Result<Patient> searchid(Integer id) {
        Patient p = patientrepository.findById(id).orElse(null);
        Result<Patient> result = new Result<>();
        if (p == null) {
            result.setSuccess(0);
        } else {
            result.setSuccess(1);
            result.setResult(p);
        }
        return result;
    }

    @GetMapping("searchloginname")
    public Result<Patient> searchloginname(String loginname) {
        Patient p = patientrepository.findByLoginname(loginname);
        Result<Patient> result = new Result<>();
        if (p == null) {
            result.setSuccess(0);
        } else {
            result.setSuccess(1);
            result.setResult(p);
        }
        return result;
    }

}
