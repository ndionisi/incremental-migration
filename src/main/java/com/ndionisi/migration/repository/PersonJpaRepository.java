package com.ndionisi.migration.repository;

import com.ndionisi.migration.domain.PersonJpa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonJpaRepository extends CrudRepository<PersonJpa, Long> {

}
