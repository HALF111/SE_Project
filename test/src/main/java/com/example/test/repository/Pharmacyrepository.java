package com.example.test.repository;

import com.example.test.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pharmacyrepository extends JpaRepository<Pharmacy, Integer> {
    public Pharmacy findByMedicinename(String medicinename);

    public List<Pharmacy> findByInventory(Integer in);
}
