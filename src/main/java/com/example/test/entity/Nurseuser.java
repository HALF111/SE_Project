package com.example.test.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Nurseuser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//用户id，主键，自增，Integer,设置这个主要是为了在其他与用户相关的表里面不需要带着账号，可以减少空间
    private String account;//用户账号，varchar（20）
    private String password;//密码，varchar（20）
}
