package com.example.test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Hospitalizationrecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "recordid")
    private Medicalrecord recordid;
    @ManyToOne
    @JoinColumn(name = "patientid")
    private Patient patientid;
    @ManyToOne
    @JoinColumn(name = "sickroomid")
    private Sickroominfo sickroomid;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:MM:SS")
    private Date arrivaltime;
    private Boolean isinhospital;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:MM:SS")
    private Date departuretime;
}
