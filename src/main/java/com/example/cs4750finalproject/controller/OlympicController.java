package com.example.cs4750finalproject.controller;

import com.example.cs4750finalproject.model.Olympic;
import com.example.cs4750finalproject.repository.OlympicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class OlympicController {

    @Autowired
    private OlympicRepository olympicRepository;

    // GET all Olympics
    @GetMapping("/olympics")
    public ResponseEntity<List<Olympic>> getAllOlympics() {
        try {
            List<Olympic> olympics = olympicRepository.findAll(); // Now directly returns List<Olympic>

            if (olympics.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(olympics, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET Olympic by year
    @GetMapping("/olympics/{year}")
    public ResponseEntity<Olympic> getOlympicByYear(@PathVariable("year") int year) {
        Optional<Olympic> olympicData = olympicRepository.findById(year);
        return olympicData.map(olympic -> new ResponseEntity<>(olympic, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST new Olympic
    @PostMapping("/olympics")
    public ResponseEntity<Olympic> createOlympic(@RequestBody Olympic olympic) {
        try {
            Olympic _olympic = olympicRepository.save(new Olympic(olympic.getYear(), olympic.getLocation()));
            return new ResponseEntity<>(_olympic, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT update Olympic
    @PutMapping("/olympics/{year}")
    public ResponseEntity<Olympic> updateOlympic(@PathVariable("year") int year, @RequestBody Olympic olympic) {
        Optional<Olympic> olympicData = olympicRepository.findById(year);
        if (olympicData.isPresent()) {
            Olympic _olympic = olympicData.get();
            _olympic.setLocation(olympic.getLocation());
            return new ResponseEntity<>(olympicRepository.save(_olympic), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE Olympic by year
    @DeleteMapping("/olympics/{year}")
    public ResponseEntity<HttpStatus> deleteOlympic(@PathVariable("year") int year) {
        try {
            olympicRepository.deleteById(year);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE all Olympics
    @DeleteMapping("/olympics")
    public ResponseEntity<HttpStatus> deleteAllOlympics() {
        try {
            olympicRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
