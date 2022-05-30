package com.example.test.controller;

import com.example.test.entity.Itemrecord;
import com.example.test.entity.Result;
import com.example.test.repository.Itemrecordrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itemrecord")
public class Itemrecordcontroller {
    @Autowired
    Itemrecordrepository itemrecordrepository;

    @PostMapping("/save")
    public Result save(Itemrecord itemrecord) {
        Result result = new Result<>();
        Itemrecord itemrecord1 = itemrecordrepository.save(itemrecord);
        if (!itemrecord1.equals(itemrecord)) {
            result.setSuccess(0);
            result.setSrc("itemrecord save error");
        }
        return result;
    }
}
