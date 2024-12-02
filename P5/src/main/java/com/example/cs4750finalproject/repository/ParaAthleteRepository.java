package com.example.cs4750finalproject.repository;

import com.example.cs4750finalproject.model.ParaAthlete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParaAthleteRepository extends JpaRepository<ParaAthlete, Integer> {
    List<ParaAthlete> findByDisability(String disability);

    List<ParaAthlete> findByEquipment(String equipment);

    List<ParaAthlete> findByDisabilityAndEquipment(String disability, String equipment);
}
