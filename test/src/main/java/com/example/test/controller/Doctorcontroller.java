package com.example.test.controller;

import com.example.test.entity.Doctor;
import com.example.test.entity.Medicalrecord;
import com.example.test.entity.Result;
import com.example.test.repository.Doctorrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctoruser")
public class Doctorcontroller {
    @Autowired
    private Doctorrepository doctorrepository;
    @Autowired
    private Medicalrecordcontroller medicalrecordcontroller;

    @PostMapping("/save")
    public Result save(@RequestBody Result<Doctor> doctoruser) {
        Doctor search = doctorrepository.findByLoginname(doctoruser.getResult().getLoginname());//先查看账号是否重复
        Result result = new Result<>();
        result.setSuccess(0);
        if (search != null) {
            result.setSrc("error,account exist");
            return result;
        }
        Doctor result1 = doctorrepository.save(doctoruser.getResult());//存入
        if (result1.equals(doctoruser.getResult())) {
            result.setSuccess(1);
        } else {
            result.setSrc("error,save error");
        }
        return result;
    }//将数据存入user数据库，url为/user/save，存入成功则返回“success”，否则“error”

    @PostMapping("/update")
    public Result update(@RequestBody Result<Doctor> doctor) {
        Doctor search = doctorrepository.findByLoginname(doctor.getResult().getLoginname());//先查看账号是否重复
        Result result = new Result();
        result.setSuccess(0);
        if (search == null) {
            result.setSrc("error,account not exist");
            return result;
        }
        Doctor rr = doctorrepository.save(doctor.getResult());//存入
        if (rr.equals(doctor.getResult())) {
            result.setSuccess(1);
        } else {
            result.setSrc("error,update error");

        }
        return result;
    }//将数据存入user数据库，url为/user/save，存入成功则返回“success”，否则“error”

    @PostMapping("/login")
    public Result login(@RequestBody Result<Doctor> doctoruser) {
        Doctor result = doctorrepository.findByLoginname(doctoruser.getResult().getLoginname());
        Result result1 = new Result<>();
        if (result != null && result.getPasswd().equals(doctoruser.getResult().getPasswd())) {
            result1.setSuccess(1);
        } else {
            result1.setSuccess(0);
            if (result == null)
                result1.setSrc("error,loginname not exist");
            else
                result1.setSrc("erroe,wrong passwd");
        }
        return result1;
    }//查询user数据库，看输入的user实例是否可以匹配一项，是则返回“success”，否则“error”

    @Transactional
    @PostMapping("/remove")
    public Result remove(@RequestBody Integer id) {
        Doctor p = doctorrepository.findById(id).orElse(null);
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
        i = doctorrepository.removeById(id);
        result.setSuccess(i);
        return result;
    }

    @GetMapping("searchid")
    public Result<Doctor> searchid(Integer id) {
        Doctor p = doctorrepository.findById(id).orElse(null);
        Result<Doctor> result = new Result<>();
        if (p == null) {
            result.setSuccess(0);
        } else {
            result.setSuccess(1);
            result.setResult(p);
        }
        return result;
    }

    @GetMapping("searchloginname")
    public Result<Doctor> searchloginname(String loginname) {
        Doctor p = doctorrepository.findByLoginname(loginname);
        Result<Doctor> result = new Result<>();
        if (p == null) {
            result.setSuccess(0);
        } else {
            result.setSuccess(1);
            result.setResult(p);
        }
        return result;
    }
}
