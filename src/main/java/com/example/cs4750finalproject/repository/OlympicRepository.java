package com.example.cs4750finalproject.repository;

import java.util.Optional;

import com.example.cs4750finalproject.model.Olympic;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OlympicRepository {
    Iterable<Object> findAll();


    Optional<Olympic> findById(int year);


    Olympic save(Olympic olympic);


    void deleteById(int year);


    void deleteAll();
}
