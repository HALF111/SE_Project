package com.example.test.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@DynamicUpdate
public class Sickroominfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer roomnumber;
    @ManyToOne
    @JoinColumn(name = "nurseid")
    private Nurse nurseid;
    private Boolean haspatient = false;
    @OneToOne
    @JoinColumn(name = "patientid")
    private Patient patientid;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sickroomid")
    private Set<Medicalrecord> medicalrecords;
}
