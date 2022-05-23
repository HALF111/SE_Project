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
    private Medicalrecord medicalrecord;
    @ManyToOne
    @JoinColumn(name = "checkitemid", referencedColumnName = "id", nullable = false)
    private Checkitemtable checkItemTable;
    private Integer itemprice;

}
