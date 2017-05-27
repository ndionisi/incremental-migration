package com.ndionisi.migration.controller;

import com.ndionisi.migration.filler.CountryFiller;
import com.ndionisi.migration.filler.FamilyNameFiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static java.util.Collections.singletonMap;

@RestController
@RequestMapping("/fill")
public class FillController {

    private final CountryFiller countryFiller;

    private final FamilyNameFiller familyNameFiller;

    @Autowired
    public FillController(CountryFiller countryFiller, FamilyNameFiller familyNameFiller) {
        this.countryFiller = countryFiller;
        this.familyNameFiller = familyNameFiller;
    }

    @GetMapping("/country")
    public Map<String, Integer> fillCountries() {
        int updateCount = countryFiller.fill();
        return singletonMap("updateCount", updateCount);
    }

    @GetMapping("/family-name")
    public Map<String, Integer> fillFamilyNames() {
        int updateCount = familyNameFiller.fill();
        return singletonMap("updateCount", updateCount);
    }
}
