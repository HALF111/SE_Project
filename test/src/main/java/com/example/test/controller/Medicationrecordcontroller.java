package com.example.test.controller;

import com.example.test.entity.Medicationrecord;
import com.example.test.entity.Result;
import com.example.test.repository.Medicationrecordrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicationrecord")
public class Medicationrecordcontroller {
    @Autowired
    Medicationrecordrepository medicationrecordrepository;

    public Result save(Medicationrecord medicationrecord) {
        Medicationrecord medicationrecord1 = medicationrecordrepository.save(medicationrecord);
        Result result = new Result<>();
        if (!medicationrecord1.equals(medicationrecord)) {
            result.setSuccess(0);
            result.setSrc("error,medicationrecord save ");
        }
        return result;
    }
}
