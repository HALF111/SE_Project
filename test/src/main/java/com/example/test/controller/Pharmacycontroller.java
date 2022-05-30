package com.example.test.controller;

import com.example.test.entity.Pharmacy;
import com.example.test.entity.Result;
import com.example.test.repository.Pharmacyrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pharmacy")
public class Pharmacycontroller {
    @Autowired
    Pharmacyrepository pharmacyrepository;

    @PostMapping("/save")
    public Result save(Result<Pharmacy> pharmacy) {
        Result result = new Result();
        Pharmacy pharmacy1 = pharmacyrepository.findByMedicinename(pharmacy.getResult().getMedicinename());
        if (pharmacy1.equals(pharmacy.getResult())) {
            result.setSuccess(0);
            result.setSrc("error,medicine exist");
            return result;
        }
        pharmacy1 = pharmacyrepository.save(pharmacy.getResult());
        if (!pharmacy1.equals(pharmacy.getResult())) {
            result.setSuccess(0);
            result.setSrc("error,save error");
            return result;
        }
        result.setSuccess(1);
        return result;
    }

    @PostMapping("/update")
    public Result update(Result<Pharmacy> pharmacy) {
        Pharmacy search = pharmacyrepository.findByMedicinename(pharmacy.getResult().getMedicinename());//先查看账号是否重复
        Result result = new Result();
        result.setSuccess(0);
        if (search == null) {
            result.setSrc("error,medicine not exist");
            return result;
        }
        Pharmacy rr = pharmacyrepository.save(pharmacy.getResult());//存入
        if (rr.equals(pharmacy.getResult())) {
            result.setSuccess(1);
        } else {
            result.setSrc("error,update error");
        }
        return result;
    }

    @GetMapping("/findempty")
    public Result<List<Pharmacy>> findempty() {
        List<Pharmacy> pharmacyList = pharmacyrepository.findByInventory(0);
        Result<List<Pharmacy>> result = new Result<>();
        result.setResult(pharmacyList);
        if (pharmacyList.size() == 0) {
            result.setSuccess(0);
            result.setSrc("empty");
        }
        return result;
    }

    @GetMapping("/findhas")
    public Result<List<Pharmacy>> findhas() {
        List<Pharmacy> pharmacyList = pharmacyrepository.findByInventoryGreaterThan(0);
        Result<List<Pharmacy>> result = new Result<>();
        result.setResult(pharmacyList);
        if (pharmacyList.size() == 0) {
            result.setSuccess(0);
            result.setSrc("empty");
        }
        return result;
    }

    public Pharmacy findbyid(Integer id) {
        return pharmacyrepository.findById(id).orElse(null);
    }
}
