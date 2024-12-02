package com.example.cs4750finalproject.controller;

import com.example.cs4750finalproject.model.Athlete;
import com.example.cs4750finalproject.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AthleteController {

    private static final Logger logger = LoggerFactory.getLogger(AthleteController.class);

    @Autowired
    private AthleteRepository athleteRepository;

    // GET API endpoints for athletes:
    @GetMapping("/athletes")
    public ResponseEntity<List<Athlete>> getAllAthletes(@RequestParam(required = false) Float height) {
        try {
            List<Athlete> athletes = new ArrayList<>();

            if (height == null) {
                athleteRepository.findAll().forEach(athletes::add);
                logger.info("Fetched all athletes.");
            } else {
                athleteRepository.findByHeight(height).forEach(athletes::add);
                logger.info("Fetched athletes with height: {}", height);
            }

            if (athletes.isEmpty()) {
                logger.warn("No athletes found.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(athletes, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching athletes", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/athletes/{playerId}")
    public ResponseEntity<Athlete> getAthleteByPlayerId(@PathVariable("playerId") int playerId) {
        try {
            Optional<Athlete> athleteData = athleteRepository.findById(playerId);
            if (athleteData.isPresent()) {
                logger.info("Fetched athlete with playerId: {}", playerId);
                return new ResponseEntity<>(athleteData.get(), HttpStatus.OK);
            } else {
                logger.warn("Athlete not found for playerId: {}", playerId);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error fetching athlete with playerId: {}", playerId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // POST api endpoint:
    @PostMapping("/athletes")
    public ResponseEntity<Athlete> createAthlete(@RequestBody Athlete athlete) {
        try {
            Athlete _athlete = athleteRepository.save(new Athlete(athlete.getPlayerId(), athlete.getHeight(), athlete.getWeight()));
            logger.info("Created new athlete: {}", _athlete);
            return new ResponseEntity<>(_athlete, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating athlete", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT api endpoint:
    @PutMapping("/athletes/{playerId}")
    public ResponseEntity<Athlete> updateAthlete(@PathVariable("playerId") int playerId, @RequestBody Athlete athlete) {
        try {
            Optional<Athlete> athleteData = athleteRepository.findById(playerId);
            if (athleteData.isPresent()) {
                Athlete _athlete = athleteData.get();
                _athlete.setHeight(athlete.getHeight());
                _athlete.setWeight(athlete.getWeight());
                logger.info("Updated athlete with playerId: {}", playerId);
                return new ResponseEntity<>(athleteRepository.save(_athlete), HttpStatus.OK);
            } else {
                logger.warn("Athlete not found for playerId: {}", playerId);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error updating athlete with playerId: {}", playerId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE api endpoints:
    @DeleteMapping("/athletes/{playerId}")
    public ResponseEntity<HttpStatus> deleteAthlete(@PathVariable("playerId") int playerId) {
        try {
            if (athleteRepository.existsById(playerId)) {
                athleteRepository.deleteById(playerId);
                logger.info("Deleted athlete with playerId: {}", playerId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                logger.warn("Athlete not found for deletion, playerId: {}", playerId);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error deleting athlete with playerId: {}", playerId, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/athletes")
    public ResponseEntity<HttpStatus> deleteAllAthletes() {
        try {
            athleteRepository.deleteAll();
            logger.info("Deleted all athletes.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Error deleting all athletes", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
