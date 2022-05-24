package com.example.test.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Sickroominfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String roomnumber;
    @ManyToOne
    @JoinColumn(name = "nurseid")
    private Nurse nurseid;
    private Boolean haspatient = false;
    @OneToOne
    @JoinColumn(name = "patientid")
    private Patient patientid;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sickroomid")
    private List<Hospitalizationrecord> hospitalizationrecords;
}
