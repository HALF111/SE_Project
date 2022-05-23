package com.example.test.controller;

import com.example.test.entity.Medicalrecord;
import com.example.test.entity.Patient;
import com.example.test.entity.Result;
import com.example.test.repository.Patientrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patientuser")
public class Patientcontroller {
    @Autowired
    private Patientrepository patientrepository;
    @Autowired
    private Medicalrecordcontroller medicalrecordcontroller;

    @PostMapping("/save")
    public Result save(@RequestBody Patient patient) {
        Patient search = patientrepository.findByLoginname(patient.getLoginname());//先查看账号是否重复
        Result result = new Result();
        result.setSuccess(0);
        if (search != null) {
            result.setSrc("error,account exist");
            return result;
        }
        Patient rr = patientrepository.save(patient);//存入
        if (rr.equals(patient)) {
            result.setSuccess(1);
        } else {
            result.setSrc("error,save error");
        }
        return result;
    }//将数据存入user数据库，url为/user/save，存入成功则返回“success”，否则“error”

    @PostMapping("/update")
    public Result update(@RequestBody Patient patient) {
        Patient search = patientrepository.findByLoginname(patient.getLoginname());//先查看账号是否重复
        Result result = new Result();
        result.setSuccess(0);
        if (search == null) {
            result.setSrc("error,account not exist");
            return result;
        }
        Patient rr = patientrepository.save(patient);//存入
        if (rr.equals(patient)) {
            result.setSuccess(1);
        } else {
            result.setSrc("error,update error");

        }
        return result;
    }//将数据存入user数据库，url为/user/save，存入成功则返回“success”，否则“error”

    @PostMapping("/login")
    public Result login(@RequestBody Patient patient) {
        Patient result = patientrepository.findByLoginname(patient.getLoginname());
        Result result1 = new Result();
        if (result != null && result.getPasswd().equals(patient.getPasswd())) {
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

    @Transactional
    @PostMapping("/remove")
    public Result remove(@RequestBody Integer id) {
        Patient p = patientrepository.findById(id).orElse(null);
        int i = 0;
        Result result = new Result();
        if (p == null) {
            result.setSuccess(i);
            return result;
        }
        Medicalrecord[] ms = p.getMedicalrecords().toArray(new Medicalrecord[0]);
        for (Medicalrecord m : ms) {
            result = medicalrecordcontroller.remove(m.getId());
            if (result.getSuccess() == 0) {
                return result;
            }
        }
        i = patientrepository.removeById(id);
        result.setSuccess(i);
        return result;
    }

    @GetMapping("search")
    public Result<Patient> search(Integer id) {
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
}
