package com.example.cs4750finalproject.repository;

import com.example.cs4750finalproject.model.Sport;
import com.example.cs4750finalproject.model.SportId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SportRepository extends JpaRepository<Sport, SportId> {
    List<Sport> findBySportNameContainingAndGenderContaining(String sportName, String gender);
    List<Sport> findByYear(int year);
    List<Sport> findByCountryNameContaining(String countryName);
}
