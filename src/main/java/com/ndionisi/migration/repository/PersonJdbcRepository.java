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
                "SELECT id, last_name, family_name, country FROM person",
                (rs, i) -> {
                    String familyName = rs.getString("family_name");
                    return new PersonJdbc(rs.getLong("id"), familyName != null ? familyName : rs.getString("last_name"), rs.getString("country"));
                }
        );
    }

    public PersonJdbc save(PersonJdbc person) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            String query = "INSERT INTO person (last_name, family_name, country) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, person.getFamilyName());
            ps.setString(2, person.getFamilyName());
            ps.setString(3, person.getCountry());
            return ps;
        }, keyHolder);

        person.setId(keyHolder.getKey().longValue());

        return person;
    }
}
