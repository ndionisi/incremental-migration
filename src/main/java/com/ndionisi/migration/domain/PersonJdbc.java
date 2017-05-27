package com.ndionisi.migration.domain;

public class PersonJdbc {

    private Long id;
    private final String lastName;
    private final String phoneNumber;

    public PersonJdbc(String lastName, String phoneNumber) {
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public PersonJdbc(long id, String lastName, String phoneNumber) {
        this(lastName, phoneNumber);
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

    public void setId(Long id) {
        this.id = id;
    }
}
