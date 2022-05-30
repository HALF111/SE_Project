package com.example.test.repository;

import com.example.test.entity.Checkitemtable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Checkitemtablerepository extends JpaRepository<Checkitemtable, Integer> {
    public Checkitemtable findByCheckitemname(String checkitemname);

    public List<Checkitemtable> findByIdGreaterThan(Integer id);
}
