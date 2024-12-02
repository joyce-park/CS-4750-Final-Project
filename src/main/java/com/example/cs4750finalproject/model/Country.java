package com.example.cs4750finalproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Country")
public class Country {
    @Id
    private String countryCode; // country_code is the primary key for the table

    private String countryName;
    private int year; // foreign key to Olympic table
    private int totalCountryMedals;

    //constructor
    public Country() {}

    //constructor with parameters
    public Country(String countryCode, String countryName, int year, int totalCountryMedals) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.year = year;
        this.totalCountryMedals = totalCountryMedals;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTotalCountryMedals() {
        return totalCountryMedals;
    }

    public void setTotalCountryMedals(int totalCountryMedals) {
        this.totalCountryMedals = totalCountryMedals;
    }

}