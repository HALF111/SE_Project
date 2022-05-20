package com.example.test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Date;

@Entity
@Data
@IdClass(MedicalrecordPra.class)
public class Medicalrecord {
    @Id
    private Integer patientid;
    @Id
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:MM:SS")
    private Date dday;
    private String src;
    private Integer doctorid;

}
