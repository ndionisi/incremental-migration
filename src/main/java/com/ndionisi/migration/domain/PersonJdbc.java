package com.ndionisi.migration.domain;

public class PersonJdbc {

    private Long id;
    private final String lastName;
    private final String country;

    public PersonJdbc(String lastName, String country) {
        this.lastName = lastName;
        this.country = country;
    }

    public PersonJdbc(long id, String lastName, String country) {
        this(lastName, country);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
