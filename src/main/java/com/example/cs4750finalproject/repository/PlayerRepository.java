package com.example.cs4750finalproject.repository;

import com.example.cs4750finalproject.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    List<Player> findBySportName(String sportName);

    List<Player> findByCountryCode(String countryCode);

    List<Player> findByTotalPlayerGoldMedalsGreaterThan(int totalPlayerGoldMedals);
}
