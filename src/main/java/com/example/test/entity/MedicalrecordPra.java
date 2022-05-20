package com.example.test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class MedicalrecordPra implements Serializable {
    private Integer patientid;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:MM:SS")
    private Date dday;
}
