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

    // GET api endpoints:
    @GetMapping
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable int id) {
        Optional<Player> player = playerRepository.findById(id);
        return player.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/sport/{sportName}")
    public List<Player> getPlayersBySport(@PathVariable String sportName) {
        return playerRepository.findBySportName(sportName);
    }

    @GetMapping("/country/{countryCode}")
    public List<Player> getPlayersByCountryCode(@PathVariable String countryCode) {
        return playerRepository.findByCountryCode(countryCode);
    }

    @GetMapping("/gold-medals/greater-than/{minGoldMedals}")
    public List<Player> getPlayersWithGoldMedalsGreaterThan(@PathVariable int minGoldMedals) {
        return playerRepository.findByTotalPlayerGoldMedalsGreaterThan(minGoldMedals);
    }

    // POST api endpoints:
    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        Player savedPlayer = playerRepository.save(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlayer);
    }

    // PUT api endpoints:
    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable int id, @RequestBody Player playerDetails) {
        Optional<Player> playerOptional = playerRepository.findById(id);

        if (playerOptional.isPresent()) {
            Player player = playerOptional.get();
            player.setFirstName(playerDetails.getFirstName());
            player.setLastName(playerDetails.getLastName());
            player.setYear(playerDetails.getYear());
            player.setSportName(playerDetails.getSportName());
            player.setCountryCode(playerDetails.getCountryCode());
            player.setTotalPlayerGoldMedals(playerDetails.getTotalPlayerGoldMedals());
            player.setGender(playerDetails.getGender());

            Player updatedPlayer = playerRepository.save(player);
            return ResponseEntity.ok(updatedPlayer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // DELETE api endpoints:
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable int id) {
        Optional<Player> playerOptional = playerRepository.findById(id);

        if (playerOptional.isPresent()) {
            playerRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
