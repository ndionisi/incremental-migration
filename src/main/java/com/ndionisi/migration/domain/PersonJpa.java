package com.ndionisi.migration.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class PersonJpa {

    @Id
    @GeneratedValue
    private Long id;
    private String lastName;
    private String familyName;
    private String country;

    public PersonJpa() {
    }

    public PersonJpa(String familyName, String country) {
        setFamilyName(familyName);
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("lastName")
    public String getFamilyName() {
        return familyName != null ? familyName : lastName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
        this.lastName = familyName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
