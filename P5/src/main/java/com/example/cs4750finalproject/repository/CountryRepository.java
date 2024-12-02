package com.example.cs4750finalproject.repository;

import com.example.cs4750finalproject.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, String> {
    List<Country> findByCountryNameContaining(String countryName);

    List<Country> findByYear(int year);

    List<Country> findByTotalCountryMedalsGreaterThan(int minMedals);
}
