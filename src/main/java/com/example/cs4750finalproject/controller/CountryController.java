package com.example.cs4750finalproject.controller;

import com.example.cs4750finalproject.model.Country;
import com.example.cs4750finalproject.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    // GET api endpoints:
    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getAllCountries(@RequestParam(required = false) String countryName) {
        try {
            List<Country> countries = new ArrayList<>();

            if (countryName == null) {
                countryRepository.findAll().forEach(countries::add);
            } else {
                countryRepository.findByCountryNameContaining(countryName).forEach(countries::add);
            }

            if (countries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(countries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/countries/{countryCode}")
    public ResponseEntity<Country> getCountryByCode(@PathVariable("countryCode") String countryCode) {
        Optional<Country> countryData = countryRepository.findById(countryCode);

        return countryData.map(country -> new ResponseEntity<>(country, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST api endpoint:
    @PostMapping("/countries")
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        try {
            Country _country = countryRepository.save(new Country(
                    country.getCountryCode(),
                    country.getCountryName(),
                    country.getYear(),
                    country.getTotalCountryMedals()));
            return new ResponseEntity<>(_country, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT api endpoint:
    @PutMapping("/countries/{countryCode}")
    public ResponseEntity<Country> updateCountry(@PathVariable("countryCode") String countryCode, @RequestBody Country country) {
        Optional<Country> countryData = countryRepository.findById(countryCode);

        if (countryData.isPresent()) {
            Country _country = countryData.get();
            _country.setCountryName(country.getCountryName());
            _country.setYear(country.getYear());
            _country.setTotalCountryMedals(country.getTotalCountryMedals());
            return new ResponseEntity<>(countryRepository.save(_country), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE api endpoints:
    @DeleteMapping("/countries/{countryCode}")
    public ResponseEntity<HttpStatus> deleteCountry(@PathVariable("countryCode") String countryCode) {
        try {
            countryRepository.deleteById(countryCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/countries")
    public ResponseEntity<HttpStatus> deleteAllCountries() {
        try {
            countryRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET api endpoints:
    @GetMapping("/countries/year/{year}")
    public ResponseEntity<List<Country>> getCountriesByYear(@PathVariable("year") int year) {
        try {
            List<Country> countries = countryRepository.findByYear(year);

            if (countries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(countries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/countries/medals/greater-than/{minMedals}")
    public ResponseEntity<List<Country>> getCountriesWithMedalsGreaterThan(@PathVariable("minMedals") int minMedals) {
        try {
            List<Country> countries = countryRepository.findByTotalCountryMedalsGreaterThan(minMedals);

            if (countries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(countries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
