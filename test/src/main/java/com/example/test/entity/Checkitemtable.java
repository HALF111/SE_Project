package com.example.test.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@DynamicUpdate
public class Checkitemtable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String checkitemname;
    private Float itemprice;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "checkitemid")
    private Set<Itemrecord> itemrecords;
}
