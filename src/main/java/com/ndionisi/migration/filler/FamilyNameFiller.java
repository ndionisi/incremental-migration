package com.ndionisi.migration.filler;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonMap;

@Component
public class FamilyNameFiller {

    private static final int CHUNK_SIZE = 10;

    private final NamedParameterJdbcOperations jdbcTemplate;

    private final TransactionTemplate transactionTemplate;

    @Autowired
    public FamilyNameFiller(NamedParameterJdbcOperations jdbcTemplate, TransactionTemplate transactionTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionTemplate = transactionTemplate;
    }

    public int fill() {
        List<Long> personIds = jdbcTemplate.queryForList("SELECT id FROM person WHERE family_name IS DISTINCT FROM last_name", emptyMap(), Long.class);
        List<List<Long>> partitionedPersonIds = Lists.partition(personIds, CHUNK_SIZE);
        for (List<Long> personIdsChunk : partitionedPersonIds) {
            transactionTemplate.execute(ts ->
                    jdbcTemplate.update("UPDATE person SET family_name = last_name WHERE id IN (:ids)",
                            singletonMap("ids", personIdsChunk)
                    )
            );
        }
        return personIds.size();
    }
}
