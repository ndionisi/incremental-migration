package com.ndionisi.migration.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class PersonDto {

    private final String lastName;
    private final String phoneNumber;

    @JsonCreator
    public PersonDto(@JsonProperty("lastName") String lastName, @JsonProperty("phoneNumber") String phoneNumber) {
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    @NotNull
    public String getLastName() {
        return lastName;
    }

    @NotNull
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
