package com.example.cs4750finalproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Player")
public class Player {
    @Id
    @Column(name = "player_id")
    private int playerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "year")
    private int year;

    @Column(name = "sport_name")
    private String sportName;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "total_player_gold_medals")
    private int totalPlayerGoldMedals;

    @Column(name = "gender")
    private String gender;

    // Default constructor
    public Player() {}

    // Constructor with parameters
    public Player(int playerId, String firstName, String lastName, int year, String sportName, String countryCode, int totalPlayerGoldMedals, String gender) {
        this.playerId = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.year = year;
        this.sportName = sportName;
        this.countryCode = countryCode;
        this.totalPlayerGoldMedals = totalPlayerGoldMedals;
        this.gender = gender;
    }

    // Getters and setters
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getTotalPlayerGoldMedals() {
        return totalPlayerGoldMedals;
    }

    public void setTotalPlayerGoldMedals(int totalPlayerGoldMedals) {
        this.totalPlayerGoldMedals = totalPlayerGoldMedals;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
