package com.example.cs4750finalproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Para_Athlete")
public class ParaAthlete {
    @Id
    @Column(name = "player_id")
    private int playerId;

    @Column(name = "disability", nullable = false)
    private String disability;

    @Column(name = "equipment")
    private String equipment;

    // Default constructor
    public ParaAthlete() {}

    // Constructor with parameters
    public ParaAthlete(int playerId, String disability, String equipment) {
        this.playerId = playerId;
        this.disability = disability;
        this.equipment = equipment;
    }

    // Getters and setters
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

}
