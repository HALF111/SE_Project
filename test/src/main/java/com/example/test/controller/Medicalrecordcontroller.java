package com.example.test.controller;

import com.example.test.entity.Medicalrecord;
import com.example.test.entity.Patient;
import com.example.test.repository.Medicalrecordrepository;
import com.example.test.repository.Patientrepository;
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
    @Autowired
    private Patientrepository patientrepository;

    @PostMapping("/insertrecord")
    public String insertrecord(@RequestBody Medicalrecord medical_record) {
        Medicalrecord search = medicalrecordrepository.findByPatientidAndRegistrationtime(medical_record.getPatientid(), medical_record.getRegistrationtime());
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
        Patient p = patientrepository.findById(i).orElse(null);
        Medicalrecord[] result = medicalrecordrepository.findByPatientid(p);
        if (result.length == 0) {
            System.out.println("error,no record");
            Medicalrecord errormessage = new Medicalrecord();
            //errormessage.setPatientid(99999);
            errormessage.setDiagnosiscontent("error,no record");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = "9999-12-31 00:00:00";
            Date date = null;
            try {
                date = simpleDateFormat.parse(s);
            } catch (ParseException e) {
                System.out.println("dataerror");
            }
            errormessage.setRegistrationtime(date);
            result = new Medicalrecord[1];
            result[0] = errormessage;
        }

        return result;
    }

}
