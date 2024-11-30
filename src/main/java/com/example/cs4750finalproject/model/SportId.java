package com.example.cs4750finalproject.model;

import java.io.Serializable;

public class SportId implements Serializable {
    private String sportName;
    private String gender;

    // Default constructor
    public SportId() {}

    // Constructor with parameters
    public SportId(String sportName, String gender) {
        this.sportName = sportName;
        this.gender = gender;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SportId sportId = (SportId) o;

        if (!sportName.equals(sportId.sportName)) return false;
        return gender.equals(sportId.gender);
    }

    @Override
    public int hashCode() {
        int result = sportName.hashCode();
        result = 31 * result + gender.hashCode();
        return result;
    }
}
