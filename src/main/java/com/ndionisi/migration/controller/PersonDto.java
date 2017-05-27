package com.ndionisi.migration.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class PersonDto {

    private final String lastName;
    private final String phoneNumber;
    private final String country;

    @JsonCreator
    public PersonDto(@JsonProperty("lastName") String lastName, @JsonProperty("phoneNumber") String phoneNumber, @JsonProperty("country") String country) {
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.country = country;
    }

    @NotNull
    public String getLastName() {
        return lastName;
    }

    @NotNull
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @NotNull
    public String getCountry() {
        return country;
    }
}
