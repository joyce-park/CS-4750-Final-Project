package com.example.cs4750finalproject.controller;

import com.example.cs4750finalproject.model.ParaAthlete;
import com.example.cs4750finalproject.repository.ParaAthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ParaAthleteController {

    @Autowired
    private ParaAthleteRepository paraAthleteRepository;

    // Get all para athletes
    @GetMapping("/paraathletes")
    public List<ParaAthlete> getAllParaAthletes() {
        return paraAthleteRepository.findAll();
    }

    // Get para athlete by ID
    @GetMapping("/paraathletes/{id}")
    public ResponseEntity<ParaAthlete> getParaAthleteById(@PathVariable int id) {
        Optional<ParaAthlete> paraAthlete = paraAthleteRepository.findById(id);
        return paraAthlete.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Add a new para athlete
    @PostMapping("/paraathletes")
    public ResponseEntity<ParaAthlete> addParaAthlete(@RequestBody ParaAthlete paraAthlete) {
        ParaAthlete savedParaAthlete = paraAthleteRepository.save(paraAthlete);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedParaAthlete);
    }

    // Find para athletes by disability
    @GetMapping("/paraathletes/disability/{disability}")
    public List<ParaAthlete> getParaAthletesByDisability(@PathVariable String disability) {
        return paraAthleteRepository.findByDisability(disability);
    }

    // Find para athletes by equipment
    @GetMapping("/paraathletes/equipment/{equipment}")
    public List<ParaAthlete> getParaAthletesByEquipment(@PathVariable String equipment) {
        return paraAthleteRepository.findByEquipment(equipment);
    }

    // Find para athletes by both disability and equipment
    @GetMapping("/paraathletes/disability/{disability}/equipment/{equipment}")
    public List<ParaAthlete> getParaAthletesByDisabilityAndEquipment(
            @PathVariable String disability,
            @PathVariable String equipment) {
        return paraAthleteRepository.findByDisabilityAndEquipment(disability, equipment);
    }

    // Update an existing para athlete
    @PutMapping("/paraathletes/{id}")
    public ResponseEntity<ParaAthlete> updateParaAthlete(@PathVariable int id, @RequestBody ParaAthlete paraAthleteDetails) {
        Optional<ParaAthlete> paraAthleteOptional = paraAthleteRepository.findById(id);

        if (paraAthleteOptional.isPresent()) {
            ParaAthlete paraAthlete = paraAthleteOptional.get();
            paraAthlete.setDisability(paraAthleteDetails.getDisability());
            paraAthlete.setEquipment(paraAthleteDetails.getEquipment());

            ParaAthlete updatedParaAthlete = paraAthleteRepository.save(paraAthlete);
            return ResponseEntity.ok(updatedParaAthlete);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Delete a para athlete
    @DeleteMapping("/paraathletes/{id}")
    public ResponseEntity<Void> deleteParaAthlete(@PathVariable int id) {
        Optional<ParaAthlete> paraAthleteOptional = paraAthleteRepository.findById(id);

        if (paraAthleteOptional.isPresent()) {
            paraAthleteRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
