package com.example.test.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@DynamicUpdate
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
