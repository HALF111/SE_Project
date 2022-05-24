package com.example.test.controller;

import com.example.test.repository.Itemrecordrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itemrecord")
public class Itemrecordcontroller {
    @Autowired
    Itemrecordrepository itemrecordrepository;
}
