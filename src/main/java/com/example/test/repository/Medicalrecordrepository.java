package com.example.test.repository;

import com.example.test.entity.Medicalrecord;
import com.example.test.entity.MedicalrecordPra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface Medicalrecordrepository extends JpaRepository<Medicalrecord, MedicalrecordPra> {
    public Medicalrecord findByPatientidAndDday(Integer patientid, Date dday);

    public Medicalrecord[] findByPatientid(Integer patientid);
}
