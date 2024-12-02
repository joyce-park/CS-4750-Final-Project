package com.example.cs4750finalproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Athlete")
public class Athlete {

    @Id
    @Column(name = "player_id")
    private int playerId;

    @Column(name = "height")
    private Float height;

    @Column(name = "weight")
    private Float weight;

    // Default constructor
    public Athlete() {}

    // Constructor with parameters
    public Athlete(int playerId, Float height, Float weight) {
        this.playerId = playerId;
        this.height = height;
        this.weight = weight;
    }

    // Getters and setters
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

}
