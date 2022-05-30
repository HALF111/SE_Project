package com.example.test.controller;

import com.example.test.entity.Itemrecord;
import com.example.test.entity.Medicalrecord;
import com.example.test.entity.Patient;
import com.example.test.entity.Result;
import com.example.test.repository.Itemrecordrepository;
import com.example.test.repository.Medicalrecordrepository;
import com.example.test.repository.Patientrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/record")
public class Medicalrecordcontroller {
    @Autowired
    private Medicalrecordrepository medicalrecordrepository;
    @Autowired
    private Patientrepository patientrepository;
    @Autowired
    private Itemrecordrepository itemrecordrepository;

    @PostMapping("/insertrecord")
    public Result insertrecord(@RequestBody Medicalrecord medical_record) {
        Medicalrecord search = medicalrecordrepository.findByPatientidAndRegistrationtime(medical_record.getPatientid(), medical_record.getRegistrationtime());
        Result result1 = new Result<>();
        result1.setSuccess(0);
        if (search != null) {
            result1.setSrc("error,record exist");
            return result1;
        }
        Medicalrecord result = medicalrecordrepository.save(medical_record);//存入
        if (result.equals(medical_record)) {
            result1.setSuccess(1);
            return result1;
        } else {
            result1.setSrc("error,save error");
            return result1;
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody Medicalrecord medical_record) {
        Medicalrecord search = medicalrecordrepository.findByPatientidAndRegistrationtime(medical_record.getPatientid(), medical_record.getRegistrationtime());
        Result result1 = new Result<>();
        result1.setSuccess(0);
        if (search == null) {
            result1.setSrc("error,record not exist");
            return result1;
        }
        Medicalrecord result = medicalrecordrepository.save(medical_record);//存入
        if (result.equals(medical_record)) {
            result1.setSuccess(1);
            return result1;
        } else {
            result1.setSrc("error,save error");
            return result1;
        }
    }


    @GetMapping("/searchrecord")
    public List<Medicalrecord> searchrecord(Integer i) {
        Patient p = patientrepository.findById(i).orElse(null);
        List<Medicalrecord> result = medicalrecordrepository.findByPatientid(p);
        if (result.size() == 0) {
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
            result.add(errormessage);

        }

        return result;
    }

    public Result remove(int id) {
        Medicalrecord m = medicalrecordrepository.findById(id).orElse(null);
        int i = 0;
        Result r = new Result();
        if (m == null) {
            r.setSuccess(0);
            return r;
        }
        Itemrecord items[] = m.getItemrecords().toArray(new Itemrecord[0]);
        for (Itemrecord is : items) {
            i = itemrecordrepository.removeById(is.getId());
            if (i == 0) {
                r.setSuccess(i);
                return r;
            }
        }
        i = medicalrecordrepository.removeById(id);
        r.setSuccess(i);
        return r;
    }

    public Medicalrecord findbyid(int id) {
        return medicalrecordrepository.findById(id).orElse(null);
    }

    @GetMapping("/price")
    public Float price(Integer id) {
        Medicalrecord medicalrecord = medicalrecordrepository.findById(id).orElse(null);
        return medicalrecord.getTotalprice();
    }

}
