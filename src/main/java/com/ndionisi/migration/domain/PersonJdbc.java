package com.ndionisi.migration.domain;

public class PersonJdbc {

    private Long id;
    private final String lastName;
    private final String phoneNumber;
    private final String country;

    public PersonJdbc(String lastName, String phoneNumber, String country) {
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.country = country;
    }

    public PersonJdbc(long id, String lastName, String phoneNumber, String country) {
        this(lastName, phoneNumber, country);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
