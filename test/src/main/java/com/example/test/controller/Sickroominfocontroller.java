package com.example.test.controller;

import com.example.test.entity.Result;
import com.example.test.entity.Sickroominfo;
import com.example.test.repository.Sickroominforepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sickroom")
public class Sickroominfocontroller {
    @Autowired
    Sickroominforepository sickroominforepository;

    @PostMapping("/save")
    public Result save(Result<Sickroominfo> sickroominfo) {
        Sickroominfo sickroominfo1 = sickroominforepository.findById(sickroominfo.getResult().getId()).orElse(null);
        Result result = new Result<>();
        result.setSuccess(0);
        if (sickroominfo1 != null) {
            result.setSrc("error,sickroomid exist");
            return result;
        }
        sickroominfo1 = sickroominforepository.findByRoomnumber(sickroominfo.getResult().getRoomnumber());
        if (sickroominfo1.equals(sickroominfo.getResult())) {
            result.setSrc("error,sickroomnumber exist");
            return result;
        }
        sickroominfo1 = sickroominforepository.save(sickroominfo.getResult());
        if (!sickroominfo1.equals(sickroominfo.getResult())) {
            result.setSrc("error,save error");
            return result;
        }
        result.setSuccess(1);
        return result;
    }

    @GetMapping("/empty")
    public List<Sickroominfo> findempty() {
        return sickroominforepository.findByHaspatient(false);
    }
}
