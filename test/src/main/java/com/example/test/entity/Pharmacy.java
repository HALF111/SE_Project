package com.example.test.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@DynamicUpdate
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String medicinename;
    private Float medicineprice;
    private int inventory;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "medicineid")
    private Set<Medicationrecord> medicationrecords;

}
