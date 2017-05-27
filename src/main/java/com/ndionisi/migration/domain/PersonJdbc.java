package com.ndionisi.migration.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonJdbc {

    private Long id;
    private final String familyName;
    private final String country;

    public PersonJdbc(String familyName, String country) {
        this.familyName = familyName;
        this.country = country;
    }

    public PersonJdbc(long id, String familyName, String country) {
        this(familyName, country);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @JsonProperty("lastName")
    public String getFamilyName() {
        return familyName;
    }

    public String getCountry() {
        return country;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
