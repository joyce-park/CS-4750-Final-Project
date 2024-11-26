package com.example.cs4750finalproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Athlete")
public class Athlete {
    @Id
    @Column(name = "player_id")
    private int playerId;

    @Column(name = "height", precision = 5, scale = 2)
    private double height;

    @Column(name = "weight", precision = 5, scale = 2)
    private double weight;

    // Default constructor
    public Athlete() {}

    // Constructor with parameters
    public Athlete(int playerId, double height, double weight) {
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

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "playerId=" + playerId +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}