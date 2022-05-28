package com.example.test.repository;

import com.example.test.entity.Sickroominfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Sickroominforepository extends JpaRepository<Sickroominfo, Integer> {
    public Sickroominfo findByRoomnumber(Integer roomnumber);

    public List<Sickroominfo> findByHaspatient(Boolean has);
}
