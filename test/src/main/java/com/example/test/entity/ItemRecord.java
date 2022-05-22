package com.example.test.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ItemRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "recordId")
    private Medicalrecord medicalrecord;
    @ManyToOne
    @JoinColumn(name = "checkItemId", referencedColumnName = "id", nullable = false)
    private CheckItemTable checkItemTable;
    private Integer itemPrice;

}
