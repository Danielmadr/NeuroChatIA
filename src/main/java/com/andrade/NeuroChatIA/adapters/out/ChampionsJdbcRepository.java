package com.andrade.NeuroChatIA.adapters.out;

import com.andrade.NeuroChatIA.domain.model.Champions;
import com.andrade.NeuroChatIA.domain.ports.ChampionsRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ChampionsJdbcRepository implements ChampionsRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Champions> rowMapper;

    public ChampionsJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = (rs, rowNum) ->
                new Champions(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getString("lore"),
                        rs.getString("image_url")
                );
    }


    @Override
    public List<Champions> findAll() {
        return jdbcTemplate.query("SELECT * FROM CHAMPIONS", rowMapper);
    }

    @Override
    public Optional<Champions> findById(Long championId) {
        Champions champion = jdbcTemplate.queryForObject(
                "SELECT * FROM CHAMPIONS WHERE ID = ?", rowMapper, championId);
        return Optional.ofNullable(champion);
    }
}