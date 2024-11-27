package com.example.cs4750finalproject.repository;

import com.example.cs4750finalproject.model.Olympic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OlympicRepository extends JpaRepository<Olympic, Integer> {
    List<Olympic> findByLocationContaining(String location);
}
