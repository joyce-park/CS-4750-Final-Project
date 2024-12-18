package com.example.cs4750finalproject.controller;

import com.example.cs4750finalproject.model.Olympic;
import com.example.cs4750finalproject.repository.OlympicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class OlympicController {

    @Autowired
    private OlympicRepository olympicRepository;

    // GET api endpoints:
    @GetMapping("/olympics")
    public ResponseEntity<List<Olympic>> getAllOlympics(@RequestParam(required = false) String location) {
        try {
            List<Olympic> olympics = new ArrayList<>();

            if (location == null) {
                olympicRepository.findAll().forEach(olympics::add);
            } else {
                olympicRepository.findByLocationContaining(location).forEach(olympics::add);
            }

            if (olympics.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(olympics, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/olympics/{year}")
    public ResponseEntity<Olympic> getOlympicByYear(@PathVariable("year") int year) {
        Optional<Olympic> olympicData = olympicRepository.findById(year);
        return olympicData.map(olympic -> new ResponseEntity<>(olympic, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST api endpoint:
    @PostMapping("/olympics")
    public ResponseEntity<Olympic> createOlympic(@RequestBody Olympic olympic) {
        try {
            Olympic _olympic = olympicRepository.save(new Olympic(olympic.getYear(), olympic.getLocation()));
            return new ResponseEntity<>(_olympic, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT api endpoint:
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

    // DELETE api endpoints:
    @DeleteMapping("/olympics/{year}")
    public ResponseEntity<HttpStatus> deleteOlympic(@PathVariable("year") int year) {
        try {
            olympicRepository.deleteById(year);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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
