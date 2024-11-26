package com.example.cs4750finalproject.model;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "Sport")
public class Sport {
    @Id
    private String sportName; //composite primary key

    @Id
    private String gender; //composite primary key

    private int year; //foreign key
    private int totalSportGoldMedals;
    private String countryName; //foreign key

    //constructor
    public Sport() {}

    //constructor with parameters
    public Sport(String sportName, String gender, int year, int totalSportGoldMedals, String countryName) {
        this.sportName = sportName;
        this.gender = gender;
        this.year = year;
        this.totalSportGoldMedals = totalSportGoldMedals;
        this.countryName = countryName;
    }

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

    // Override toString method
    @Override
    public String toString() {
        return "Sport{" +
                "sportName='" + sportName + '\'' +
                ", gender='" + gender + '\'' +
                ", year=" + year +
                ", totalSportGoldMedals=" + totalSportGoldMedals +
                ", countryName='" + countryName + '\'' +
                '}';
    }

    // Composite key class
    public static class SportId implements Serializable {
        private String sportName;
        private String gender;

        //constructor
        public SportId() {}

        //constructor with parameters
        public SportId(String sportName, String gender) {
            this.sportName = sportName;
            this.gender = gender;
        }

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
}
