package com.ndionisi.migration.filler;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

import static java.util.Collections.emptyMap;

@Component
public class CountryFiller {

    private static final int CHUNK_SIZE = 10;
    private static final String COUNTRY_DEFAULT_VALUE = "US";

    private final NamedParameterJdbcOperations jdbcTemplate;

    private final TransactionTemplate transactionTemplate;

    @Autowired
    public CountryFiller(NamedParameterJdbcOperations jdbcTemplate, TransactionTemplate transactionTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionTemplate = transactionTemplate;
    }

    public int fill() {
        List<Long> personIds = jdbcTemplate.queryForList("SELECT id FROM person WHERE country IS NULL", emptyMap(), Long.class);
        List<List<Long>> partitionedPersonIds = Lists.partition(personIds, CHUNK_SIZE);
        for (List<Long> personIdsChunk : partitionedPersonIds) {
            transactionTemplate.execute(ts ->
                    jdbcTemplate.update("UPDATE person SET country = :country WHERE id IN (:ids)",
                            ImmutableMap.of(
                                    "country", COUNTRY_DEFAULT_VALUE,
                                    "ids", personIdsChunk
                            )
                    )
            );
        }
        return personIds.size();
    }
}
