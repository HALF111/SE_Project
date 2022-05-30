package com.example.test.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Data
@DynamicUpdate
public class Medicationrecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "recordid")
    private Medicalrecord recordid;
    @ManyToOne
    @JoinColumn(name = "medicineid")
    private Pharmacy medicineid;
    private Integer number;
    private Float medicineprice;

}
