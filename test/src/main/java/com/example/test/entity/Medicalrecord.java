package com.example.test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

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
    private Boolean needhospitalization = false;
    private Float totalprice;
    private Boolean ispaid = false;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:MM:SS")
    private Date paymenttime;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recordid")
    private Set<Itemrecord> itemrecords;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recordid")
    private Set<Medicationrecord> medicationrecords;
    @ManyToOne
    @JoinColumn(name = "sickroomid")
    private Sickroominfo sickroomid;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:MM:SS")
    private Date arrivaltime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:MM:SS")
    private Date departuretime;


}
