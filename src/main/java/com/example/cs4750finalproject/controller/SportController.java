package com.example.cs4750finalproject.controller;

import com.example.cs4750finalproject.model.Sport;
import com.example.cs4750finalproject.model.SportId;
import com.example.cs4750finalproject.repository.SportRepository;
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
public class SportController {

    @Autowired
    private SportRepository sportRepository;

    // GET all sports or search by sport name, gender, or year
    @GetMapping("/sports")
    public ResponseEntity<List<Sport>> getAllSports(@RequestParam(required = false) String sportName,
                                                    @RequestParam(required = false) String gender,
                                                    @RequestParam(required = false) Integer year,
                                                    @RequestParam(required = false) String countryName) {
        try {
            List<Sport> sports = new ArrayList<>();

            if (sportName == null && gender == null && year == null && countryName == null) {
                sportRepository.findAll().forEach(sports::add);
            } else if (sportName != null && gender != null) {
                sportRepository.findBySportNameContainingAndGenderContaining(sportName, gender).forEach(sports::add);
            } else if (year != null) {
                sportRepository.findByYear(year).forEach(sports::add);
            } else if (countryName != null) {
                sportRepository.findByCountryNameContaining(countryName).forEach(sports::add);
            }

            if (sports.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(sports, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET Sport by sportName and gender
    @GetMapping("/sports/{sportName}/{gender}")
    public ResponseEntity<Sport> getSportBySportNameAndGender(@PathVariable("sportName") String sportName,
                                                              @PathVariable("gender") String gender) {
        SportId sportId = new SportId(sportName, gender);
        Optional<Sport> sportData = sportRepository.findById(sportId);
        return sportData.map(sport -> new ResponseEntity<>(sport, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST new Sport
    @PostMapping("/sports")
    public ResponseEntity<Sport> createSport(@RequestBody Sport sport) {
        try {
            Sport _sport = sportRepository.save(new Sport(sport.getSportName(), sport.getGender(), sport.getYear(),
                    sport.getTotalSportGoldMedals(), sport.getCountryName()));
            return new ResponseEntity<>(_sport, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT update Sport
    @PutMapping("/sports/{sportName}/{gender}")
    public ResponseEntity<Sport> updateSport(@PathVariable("sportName") String sportName,
                                             @PathVariable("gender") String gender, @RequestBody Sport sport) {
        SportId sportId = new SportId(sportName, gender);
        Optional<Sport> sportData = sportRepository.findById(sportId);
        if (sportData.isPresent()) {
            Sport _sport = sportData.get();
            _sport.setTotalSportGoldMedals(sport.getTotalSportGoldMedals());
            _sport.setCountryName(sport.getCountryName());
            return new ResponseEntity<>(sportRepository.save(_sport), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE Sport by sportName and gender
    @DeleteMapping("/sports/{sportName}/{gender}")
    public ResponseEntity<HttpStatus> deleteSport(@PathVariable("sportName") String sportName,
                                                  @PathVariable("gender") String gender) {
        try {
            SportId sportId = new SportId(sportName, gender);
            sportRepository.deleteById(sportId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE all Sports
    @DeleteMapping("/sports")
    public ResponseEntity<HttpStatus> deleteAllSports() {
        try {
            sportRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
