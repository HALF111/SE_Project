package com.example.test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@DynamicUpdate
public class Medicalrecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "patientid", updatable = false, insertable = false)
    private Patient patientid;
    @ManyToOne
    @JoinColumn(name = "doctorid", updatable = false, insertable = false)
    private Doctor doctorid;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:MM:SS")
    private Date registrationtime;
    private Boolean isending = false;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:MM:SS")
    private Date diagnosistime;
    private String diagnosiscontent;
    private Boolean needhospitalization;
    private Integer totalprice;
    private Boolean ispaid = false;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:MM:SS")
    private Date paymenttime;
    @OneToMany
    @JoinColumn(name = "recordId")
    private List<ItemRecord> itemRecords;


}
