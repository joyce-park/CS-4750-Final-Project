package com.example.cs4750finalproject.repository;

import com.example.cs4750finalproject.model.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AthleteRepository extends JpaRepository<Athlete, Integer> {
    List<Athlete> findByHeight(Float height);
}
