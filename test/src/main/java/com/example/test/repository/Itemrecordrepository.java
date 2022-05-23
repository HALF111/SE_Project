package com.example.test.repository;

import com.example.test.entity.Itemrecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Itemrecordrepository extends JpaRepository<Itemrecord, Integer> {
    public Itemrecord findById(int id);

    public int removeById(int id);
}
