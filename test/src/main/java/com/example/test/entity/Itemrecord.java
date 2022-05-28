package com.example.test.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Itemrecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "recordid")
    private Medicalrecord recordid;
    @ManyToOne
    @JoinColumn(name = "checkitemid")
    private Checkitemtable checkitemid;
    private Float itemprice;

}
