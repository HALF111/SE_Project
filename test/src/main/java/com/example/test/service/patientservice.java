package com.example.test.service;

import com.example.test.controller.Doctorcontroller;
import com.example.test.controller.Medicalrecordcontroller;
import com.example.test.controller.Patientcontroller;
import com.example.test.controller.Sickroominfocontroller;
import com.example.test.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/patientuser")
public class patientservice {
    @Autowired
    private Patientcontroller patientcontroller;
    @Autowired
    private Medicalrecordcontroller medicalrecordcontroller;
    @Autowired
    private Sickroominfocontroller sickroominfocontroller;
    @Autowired
    private Doctorcontroller doctorcontroller;

    @Transactional
    @PostMapping("/remove")
    public Result remove(@RequestBody Integer patientid) {
        Patient p = patientcontroller.findById(patientid);
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
        Patient p1 = patientcontroller.removeByid(patientid);
        if (p.equals(p1)) {
            result.setSuccess(1);
        } else {
            result.setSuccess(0);
            result.setSrc("error,remove error");
        }

        return result;
    }

    @PostMapping("/changepwd")
    public Result changepasswd(Integer patientid, String passwd) {
        Patient patient = patientcontroller.findById(patientid);
        patient.setPasswd(passwd);
        Result<Patient> patientResult = new Result<>();
        patientResult.setResult(patient);
        Result result = patientcontroller.update(patientResult);
        return result;
    }

    @PostMapping("/hospitalization")
    public Result hospitalization(Integer patientid, Integer sickroomid) {
        Patient patient = patientcontroller.findById(patientid);
        Result result = new Result<>();
        if (patient.getIsinhospital()) {
            result.setSuccess(0);
            result.setSrc("error,already in hospital");
            return result;
        }
        Sickroominfo sickroominfo = sickroominfocontroller.findbyid(sickroomid);
        if (sickroominfo == null) {
            result.setSuccess(0);
            result.setSrc("error,sickroom not exist");
            return result;
        }
        if (sickroominfo.getHaspatient()) {
            result.setSuccess(0);
            result.setSrc("error,sickroom not empty");
            return result;
        }
        Set<Medicalrecord> medicalrecords = patient.getMedicalrecords();
        Medicalrecord mm = new Medicalrecord();
        mm.setId(999999999);
        for (Medicalrecord medicalrecord : medicalrecords) {
            if (medicalrecord.getId() < mm.getId()) {
                mm = medicalrecord;
            }
        }
        mm.setNeedhospitalization(true);
        mm.setArrivaltime(new Date());
        mm.setSickroomid(sickroominfo);
        result = medicalrecordcontroller.update(mm);
        if (result.getSuccess() == 0) {
            return result;
        }
        sickroominfo.setHaspatient(true);
        sickroominfo.setPatientid(patient);
        Result<Sickroominfo> sickroominfoResult = new Result<>();
        sickroominfoResult.setResult(sickroominfo);
        result = sickroominfocontroller.update(sickroominfoResult);
        if (result.getSuccess() == 0) {
            return result;
        }
        patient.setIsinhospital(true);
        Result<Patient> patientResult = new Result<>();
        patientResult.setResult(patient);
        result = patientcontroller.update(patientResult);
        return result;
    }

    @PostMapping("/unhospitalization")
    public Result unhospitalization(Integer patientid) {
        Patient patient = patientcontroller.findById(patientid);
        Result result = new Result<>();
        if (!patient.getIsinhospital()) {
            result.setSuccess(0);
            result.setSrc("error,not in hospital");
            return result;
        }
        Sickroominfo sickroominfo = patient.getSickroominfo();
        if (sickroominfo == null) {
            result.setSuccess(0);
            result.setSrc("error,sickroom not exist");
            return result;
        }
        if (!sickroominfo.getHaspatient()) {
            result.setSuccess(0);
            result.setSrc("error,sickroom empty");
            return result;
        }
        Set<Medicalrecord> medicalrecords = patient.getMedicalrecords();
        Medicalrecord mm = new Medicalrecord();
        mm.setId(0);
        for (Medicalrecord medicalrecord : medicalrecords) {
            if (medicalrecord.getId() > mm.getId()) {
                mm = medicalrecord;
            }
        }
        mm.setNeedhospitalization(false);
        mm.setSickroomid(null);
        mm.setDeparturetime(new Date());
        result = medicalrecordcontroller.update(mm);
        if (result.getSuccess() == 0) {
            return result;
        }
        sickroominfo.setHaspatient(false);
        sickroominfo.setPatientid(null);
        Result<Sickroominfo> sickroominfoResult = new Result<>();
        sickroominfoResult.setResult(sickroominfo);
        result = sickroominfocontroller.update(sickroominfoResult);
        if (result.getSuccess() == 0) {
            return result;
        }
        patient.setIsinhospital(false);
        Result<Patient> patientResult = new Result<>();
        patientResult.setResult(patient);
        result = patientcontroller.update(patientResult);
        return result;
    }

    @GetMapping("/findlastMedicationrecord")
    public Result<List<Medicationrecord>> findlastMedicationrecord(Integer patientid) {
        Patient patient = patientcontroller.findById(patientid);
        Set<Medicalrecord> medicalrecords = patient.getMedicalrecords();
        Medicalrecord mm = new Medicalrecord();
        mm.setId(0);
        for (Medicalrecord medicalrecord : medicalrecords) {
            if (medicalrecord.getId() > mm.getId()) {
                mm = medicalrecord;
            }
        }
        List<Medicationrecord> medicationrecords = new ArrayList<>(mm.getMedicationrecords());
        Result<List<Medicationrecord>> result = new Result<>();
        result.setResult(medicationrecords);
        return result;
    }//返回病人最后一个病历的项目

    @GetMapping("/findMedicationrecord")
    public Result<List<Medicationrecord>> findMedicationrecord(Integer medicalid) {
        List<Medicationrecord> medicationrecords = new ArrayList<>(medicalrecordcontroller.findbyid(medicalid).getMedicationrecords());
        Result<List<Medicationrecord>> result = new Result<>();
        if (medicationrecords.size() == 0) {
            result.setSuccess(0);
            result.setSrc("empty");
        }
        result.setResult(medicationrecords);
        return result;
    }//返回某个病历的项目


    @GetMapping("/findlastItemrecord")
    public Result<List<Itemrecord>> findlastItemrecord(Integer patientid) {
        Patient patient = patientcontroller.findById(patientid);
        Set<Medicalrecord> medicalrecords = patient.getMedicalrecords();
        Medicalrecord mm = new Medicalrecord();
        mm.setId(0);
        for (Medicalrecord medicalrecord : medicalrecords) {
            if (medicalrecord.getId() > mm.getId()) {
                mm = medicalrecord;
            }
        }
        List<Itemrecord> itemrecords = new ArrayList<>(mm.getItemrecords());
        Result<List<Itemrecord>> result = new Result<>();
        result.setResult(itemrecords);
        return result;
    }//返回病人最后一个病历的项目

    @GetMapping("/findItemrecord")
    public Result<List<Itemrecord>> findItemrecord(Integer medicalid) {
        List<Itemrecord> itemrecords = new ArrayList<>(medicalrecordcontroller.findbyid(medicalid).getItemrecords());
        Result<List<Itemrecord>> result = new Result<>();
        if (itemrecords.size() == 0) {
            result.setSuccess(0);
            result.setSrc("empty");
        }
        result.setResult(itemrecords);
        return result;
    }//返回某个病历的项目

    @PostMapping("/pay")
    public Result pay(Integer medicalrecordid) {
        Medicalrecord medicalrecord = medicalrecordcontroller.findbyid(medicalrecordid);
        Result result = new Result<>();
        result.setSuccess(0);
        if (medicalrecord == null) {
            result.setSrc("error,medicalrecord not exist");
            return result;
        }
        medicalrecord.setIspaid(true);
        medicalrecord.setPaymenttime(new Date());
        result = medicalrecordcontroller.update(medicalrecord);
        return result;
    }//将这个记录记为已支付

    @GetMapping("/unpaied")
    public Result<List<Medicalrecord>> unpaied(Integer patientid) {
        List<Medicalrecord> medicalrecords = medicalrecordcontroller.searchrecord(patientid);
        List<Medicalrecord> unpaied = new ArrayList<>();
        for (Medicalrecord medicalrecord : medicalrecords) {
            if (!medicalrecord.getIspaid()) {
                unpaied.add(medicalrecord);
            }
        }

        Result<List<Medicalrecord>> result = new Result<>();
        if (unpaied.size() == 0) {
            result.setSuccess(0);
            result.setSrc("all paied");
            return result;
        }
        result.setResult(unpaied);
        result.setSuccess(1);
        return result;
    }//返回病人没有付款的病历

    @GetMapping("/registration")
    public Result<Medicalrecord> registration(Integer patientid, Integer doctoerid) {
        Medicalrecord medicalrecord = new Medicalrecord();
        medicalrecord.setDoctorid(doctorcontroller.searchid(doctoerid).getResult());
        medicalrecord.setPatientid(patientcontroller.findById(patientid));
        medicalrecord.setRegistrationtime(new Date());
        Result<Medicalrecord> result = new Result<>();
        result.setResult(medicalrecord);
        return result;
    }

}
