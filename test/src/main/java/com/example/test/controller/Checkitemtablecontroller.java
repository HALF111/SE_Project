package com.example.test.controller;

import com.example.test.entity.Checkitemtable;
import com.example.test.entity.Result;
import com.example.test.repository.Checkitemtablerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkitemtable")
public class Checkitemtablecontroller {
    @Autowired
    Checkitemtablerepository checkitemtablerepository;

    @PostMapping("/save")
    public Result save(Result<Checkitemtable> checkitemtable) {
        Checkitemtable checkitemtable1 = checkitemtablerepository.findByCheckitemname(checkitemtable.getResult().getCheckitemname());
        Result result = new Result<>();
        result.setSuccess(0);
        if (checkitemtable1.equals(checkitemtable.getResult())) {
            result.setSrc("error,itemexist");
            return result;
        }
        checkitemtable1 = checkitemtablerepository.save(checkitemtable.getResult());
        if (!checkitemtable1.equals(checkitemtable.getResult())) {
            result.setSrc("error,save error");
            return result;
        }
        result.setSuccess(1);
        return result;

    }

    @PostMapping("/update")
    public Result update(Result<Checkitemtable> checkitemtable) {
        Checkitemtable checkitemtable1 = checkitemtablerepository.findById(checkitemtable.getResult().getId()).orElse(null);
        Result result = new Result<>();
        if (checkitemtable1 == null) {
            result.setSuccess(0);
            result.setSrc("error,item exist");
            return result;
        }
        checkitemtable1 = checkitemtablerepository.save(checkitemtable.getResult());
        if (!checkitemtable1.equals(checkitemtable.getResult())) {
            result.setSuccess(0);
            result.setSrc("error,save error");
            return result;
        }
        result.setSuccess(1);
        return result;
    }

}
