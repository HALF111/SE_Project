package com.example.test.service;

import com.example.test.controller.Medicalrecordcontroller;
import com.example.test.controller.Patientcontroller;
import com.example.test.entity.Medicalrecord;
import com.example.test.entity.Patient;
import com.example.test.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patientuser")
public class patientservice {
    @Autowired
    private Patientcontroller patientcontroller;
    @Autowired
    private Medicalrecordcontroller medicalrecordcontroller;

    @Transactional
    @PostMapping("/remove")
    public Result remove(@RequestBody Integer id) {
        Patient p = patientcontroller.findById(id);
        Result result = new Result();
        if (p == null) {
            result.setSuccess(0);
            result.setSrc("error,patient not exist");
            return result;
        }
        Medicalrecord[] ms = p.getMedicalrecords().toArray(new Medicalrecord[0]);
        for (Medicalrecord m : ms) {
            result = medicalrecordcontroller.remove(m.getId());
            if (result.getSuccess() == 0) {
                return result;
            }
        }
        Patient p1 = patientcontroller.removeByid(id);
        if (p.equals(p1)) {
            result.setSuccess(1);
        } else {
            result.setSuccess(0);
            result.setSrc("error,remove error");
        }

        return result;
    }

    public Result changepasswd(Integer id, String passwd) {
        Patient patient = patientcontroller.findById(id);
        patient.setPasswd(passwd);
        Result<Patient> patientResult = new Result<>();
        patientResult.setResult(patient);
        Result result = patientcontroller.update(patientResult);
        return result;
    }

}
