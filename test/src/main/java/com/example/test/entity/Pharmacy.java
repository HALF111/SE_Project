package com.example.test.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String medicinename;
    private int medicineprice;
    private int inventory;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "medicineid")
    private List<Medicationrecord> medicationrecords;

}
