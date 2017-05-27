package com.ndionisi.migration.controller;

import com.ndionisi.migration.domain.PersonJdbc;
import com.ndionisi.migration.domain.PersonJpa;
import com.ndionisi.migration.repository.PersonJdbcRepository;
import com.ndionisi.migration.repository.PersonJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonJpaRepository personJpaRepository;

    private final PersonJdbcRepository personJdbcRepository;

    @Autowired
    public PersonController(PersonJpaRepository personJpaRepository, PersonJdbcRepository personJdbcRepository) {
        this.personJpaRepository = personJpaRepository;
        this.personJdbcRepository = personJdbcRepository;
    }

    @GetMapping("/jpa")
    public Iterable<PersonJpa> getPersonsJpa() {
        return personJpaRepository.findAll();
    }

    @GetMapping("/jdbc")
    public Iterable<PersonJdbc> getPersonsJdbc() {
        return personJdbcRepository.findAll();
    }

    @PostMapping("/jpa")
    public PersonJpa createPersonJpa(@Valid @RequestBody PersonDto personDto) {
        PersonJpa person = new PersonJpa(personDto.getLastName(), personDto.getPhoneNumber(), personDto.getCountry());
        return personJpaRepository.save(person);
    }

    @PostMapping("/jdbc")
    public PersonJdbc createPersonJdbc(@Valid @RequestBody PersonDto personDto) {
        PersonJdbc person = new PersonJdbc(personDto.getLastName(), personDto.getPhoneNumber(), personDto.getCountry());
        return personJdbcRepository.save(person);
    }
}
