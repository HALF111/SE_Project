package com.example.test.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Checkitemtable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String checkitemname;
    private Float itemprice;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "checkitemid")
    private Set<Itemrecord> itemrecords;
}
