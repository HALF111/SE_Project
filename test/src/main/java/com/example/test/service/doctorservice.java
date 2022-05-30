package com.example.test.service;

import com.example.test.controller.*;
import com.example.test.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/doctoruser")
public class doctorservice {
    @Autowired
    private Doctorcontroller doctorcontroller;
    @Autowired
    private Medicalrecordcontroller medicalrecordcontroller;
    @Autowired
    private Pharmacycontroller pharmacycontroller;
    @Autowired
    private Medicationrecordcontroller medicationrecordcontroller;
    @Autowired
    private Checkitemtablecontroller checkitemtablecontroller;
    @Autowired
    private Itemrecordcontroller itemrecordcontroller;

    @PostMapping("/diagnosis")
    public Result<Medicalrecord> diagnosis(Integer medicalrecordid, String diagnosiscontent) {
        Medicalrecord medicalrecord = medicalrecordcontroller.findbyid(medicalrecordid);
        Result<Medicalrecord> result = new Result<>();
        if (medicalrecord == null) {
            result.setSuccess(0);
            result.setSrc("error,medical not exist");
            return result;
        }
        medicalrecord.setDiagnosiscontent(diagnosiscontent);
        medicalrecord.setDiagnosistime(new Date());
        result = medicalrecordcontroller.update(medicalrecord);
        if (result.getSuccess() == 1) {
            result.setResult(medicalrecord);
        }
        return result;
    }

    @PostMapping("/prescribe")
    public Result prescribe(Integer medicalrecordid, Integer[] pharmacyids, Integer[] numbers) {
        Medicationrecord medicationrecord;
        Medicalrecord medicalrecord = medicalrecordcontroller.findbyid(medicalrecordid);
        Pharmacy pharmacy;
        Result result = new Result<>();
        int g = 0;
        for (Integer i : pharmacyids) {
            pharmacy = pharmacycontroller.findbyid(i);
            medicationrecord = new Medicationrecord();
            medicationrecord.setRecordid(medicalrecord);
            medicationrecord.setMedicineid(pharmacy);
            medicationrecord.setNumber(numbers[g]);
            medicationrecord.setMedicineprice(pharmacy.getMedicineprice());
            result = medicationrecordcontroller.save(medicationrecord);
            if (result.getSuccess() == 0) {
                result.setSrc("error, " + g + " pharmacy save error " + pharmacy.getMedicinename());
                return result;
            }
            g++;
        }
        return result;
    }

    @PostMapping("/item")
    public Result item(Integer medicalrecordid, Integer[] itemids) {
        Itemrecord itemrecord;
        Medicalrecord medicalrecord = medicalrecordcontroller.findbyid(medicalrecordid);
        Checkitemtable checkitemtable;
        Result result = new Result<>();
        for (Integer i : itemids) {
            checkitemtable = checkitemtablecontroller.findbyid(i);
            itemrecord = new Itemrecord();
            itemrecord.setRecordid(medicalrecord);
            itemrecord.setCheckitemid(checkitemtable);
            itemrecord.setItemprice(checkitemtable.getItemprice());
            result = itemrecordcontroller.save(itemrecord);
            if (result.getSuccess() == 0) {
                result.setSrc("error, " + checkitemtable.getCheckitemname() + "save error");
                return result;
            }
        }
        return result;
    }
}


