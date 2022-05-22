package com.example.test.entity;

import lombok.Data;

@Data
public class Result<T> {
    int success;
    T result;

}
