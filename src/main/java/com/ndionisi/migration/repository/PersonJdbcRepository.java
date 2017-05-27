package com.ndionisi.migration.repository;

import com.ndionisi.migration.domain.PersonJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class PersonJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Iterable<PersonJdbc> findAll() {
        return jdbcTemplate.query(
                "SELECT id, last_name, phone_number FROM person",
                (rs, i) -> new PersonJdbc(rs.getLong("id"), rs.getString("last_name"), rs.getString("phone_number"))
        );
    }

    public PersonJdbc save(PersonJdbc person) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            String query = "INSERT INTO person (last_name, phone_number) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, person.getLastName());
            ps.setString(2, person.getPhoneNumber());
            return ps;
        }, keyHolder);

        person.setId(keyHolder.getKey().longValue());

        return person;
    }
}
