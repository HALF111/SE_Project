package com.example.test.controller;

import com.example.test.entity.Medicalrecord;
import com.example.test.repository.Medicalrecordrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/record")
public class Medicalrecordcontroller {
    @Autowired
    private Medicalrecordrepository medicalrecordrepository;

    @PostMapping("/insertrecord")
    public String insertrecord(@RequestBody Medicalrecord medical_record) {
        Medicalrecord search = medicalrecordrepository.findByPatientidAndDday(medical_record.getPatientid(), medical_record.getDday());
        if (search != null) {
            return "error,record exist";
        }
        Medicalrecord result = medicalrecordrepository.save(medical_record);//存入
        if (result.equals(medical_record)) {
            return "success";
        } else {
            return "error,save error";
        }
    }

    @GetMapping("/searchrecord")
    public Medicalrecord[] searchrecord(Integer i) {

        Medicalrecord[] result = medicalrecordrepository.findByPatientid(i);
        if (result.length == 0) {
            System.out.println("error,no record");
            Medicalrecord errormessage = new Medicalrecord();
            errormessage.setPatientid(99999);
            errormessage.setSrc("error,no record");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = "9999-12-31 00:00:00";
            Date date = null;
            try {
                date = simpleDateFormat.parse(s);
            } catch (ParseException e) {
                System.out.println("dataerror");
            }
            errormessage.setDday(date);
            result = new Medicalrecord[1];
            result[0] = errormessage;
        }

        return result;
    }

}
