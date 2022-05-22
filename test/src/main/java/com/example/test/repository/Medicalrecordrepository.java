package com.example.test.repository;

import com.example.test.entity.Medicalrecord;
import com.example.test.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface Medicalrecordrepository extends JpaRepository<Medicalrecord, Integer> {
    public Medicalrecord findByPatientidAndRegistrationtime(Patient patientid, Date registrationtime);

    public Medicalrecord[] findByPatientid(Patient patientid);
}
