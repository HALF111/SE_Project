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
    @JoinColumn(name = "patientid")
    private Patient patientid;
    @ManyToOne
    @JoinColumn(name = "doctorid")
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
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "recordid")
    private List<Itemrecord> itemrecords;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "recordid")
    private List<Medicationrecord> medicationrecords;
    @OneToOne(mappedBy = "recordid")
    private Hospitalizationrecord hospitalizationrecord;


}
