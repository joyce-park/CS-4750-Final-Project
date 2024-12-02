package com.example.cs4750finalproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Olympic")
public class Olympic {
    @Id
    private int year; //year is primary key for the table
    private String location;

    //constructor
    public Olympic() {}

    //constructor with parameters
    public Olympic(int year, String location){
        this.year = year;
        this.location = location;
    }

    public int getYear(){
        return year;
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }

}