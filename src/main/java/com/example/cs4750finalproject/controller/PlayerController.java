package com.example.cs4750finalproject.controller;

import com.example.cs4750finalproject.model.Player;
import com.example.cs4750finalproject.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    // Get all players
    @GetMapping
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    // Get player by ID
    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable int id) {
        Optional<Player> player = playerRepository.findById(id);
        return player.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Add a new player
    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        Player savedPlayer = playerRepository.save(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlayer);
    }

    // Find players by sport name
    @GetMapping("/sport/{sportName}")
    public List<Player> getPlayersBySport(@PathVariable String sportName) {
        return playerRepository.findBySportName(sportName);
    }

    // Find players by country code
    @GetMapping("/country/{countryCode}")
    public List<Player> getPlayersByCountryCode(@PathVariable String countryCode) {
        return playerRepository.findByCountryCode(countryCode);
    }

    // Find players with gold medals greater than the specified number
    @GetMapping("/gold-medals/greater-than/{minGoldMedals}")
    public List<Player> getPlayersWithGoldMedalsGreaterThan(@PathVariable int minGoldMedals) {
        return playerRepository.findByTotalPlayerGoldMedalsGreaterThan(minGoldMedals);
    }
}
