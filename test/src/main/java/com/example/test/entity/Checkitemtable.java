package com.example.test.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Checkitemtable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String checkitemname;
    private Integer itemprice;
    @OneToMany
    @JoinColumn(name = "checkitemid")
    private List<Itemrecord> itemrecords;
}
