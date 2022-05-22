package com.example.test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Nurse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//用户id，主键，自增，Integer,设置这个主要是为了在其他与用户相关的表里面不需要带着账号，可以减少空间
    private String loginname;//用户账号，varchar（20）
    private String realname;
    private String passwd;//密码，varchar（20）
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:MM:SS")
    private Date registertime;
}
