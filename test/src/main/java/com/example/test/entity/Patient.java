package com.example.test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@DynamicUpdate
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//用户id，主键，自增，Integer,设置这个主要是为了在其他与用户相关的表里面不需要带着账号，可以减少空间
    private String loginname;//用户账号，varchar（20）
    private String realname;
    private String passwd;//密码，varchar（20）
    private Integer age;
    private Integer gender;
    private Boolean isinhospital = false;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:MM:SS")
    private Date registertime;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "patientid")
    private List<Medicalrecord> medicalrecords;
}
