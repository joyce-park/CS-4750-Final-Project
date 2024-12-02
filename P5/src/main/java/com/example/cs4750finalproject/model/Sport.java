package com.example.cs4750finalproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Sport")
@IdClass(SportId.class)
public class Sport {
    @Id
    private String sportName;

    @Id
    private String gender;

    private int year;
    private int totalSportGoldMedals;
    private String countryName;

    // Default constructor
    public Sport() {}

    // Constructor with parameters
    public Sport(String sportName, String gender, int year, int totalSportGoldMedals, String countryName) {
        this.sportName = sportName;
        this.gender = gender;
        this.year = year;
        this.totalSportGoldMedals = totalSportGoldMedals;
        this.countryName = countryName;
    }

    // Getters and setters
    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTotalSportGoldMedals() {
        return totalSportGoldMedals;
    }

    public void setTotalSportGoldMedals(int totalSportGoldMedals) {
        this.totalSportGoldMedals = totalSportGoldMedals;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

}
