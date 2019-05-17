package com.concert.dao.impl;

import com.concert.dao.PerformerDAO;
import com.concert.dao.mappers.PerformerMapper;
import com.concert.model.Performer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

@Repository
@PropertySource("classpath:sql/performerdao.properties")
public class PerformerDAOImpl implements PerformerDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${GET_ALL_PERFORMERS}")
    private String GET_ALL_PERFORMERS;

    @Value("${GET_PERFORMERS_FROM_CONCERT}")
    private String GET_PERFORMERS_FROM_CONCERT;

    @Value("${INSERT_NEW_PERFORMER}")
    private String INSERT_NEW_PERFORMER;

    @Value("${GET_PERFORMER_BY_ID}")
    private String GET_PERFORMER_BY_ID;

    @Value("${UPDATE_PERFORMER}")
    private String UPDATE_PERFORMER;

    @Value("${DELETE_PERFORMER}")
    private String DELETE_PERFORMER;

    @Value("${GET_FREE_PERFORMERS}")
    private String GET_FREE_PERFORMERS;

    private static final String CONCERT_ID = "concertId";
    private static final String PERFORMER_ID = "pId";
    private static final String PERFORMER_NAME = "name";

    @Autowired
    public void setNamedParameterJdbcTemplate(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Performer> getAllPerformers() {
        return namedParameterJdbcTemplate.query(GET_ALL_PERFORMERS, new PerformerMapper());
    }

    @Override
    public List<Performer> getAllPerformersFromConcert(Integer concertId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(CONCERT_ID, concertId);
        return namedParameterJdbcTemplate.query(GET_PERFORMERS_FROM_CONCERT, parameters, new PerformerMapper());
    }

    @Override
    public Performer create(String performerName) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(PERFORMER_NAME, performerName);
        namedParameterJdbcTemplate.update(INSERT_NEW_PERFORMER, parameterSource, keyHolder, new String[]{"id"});

        Performer performer = new Performer();
        performer.setPerformerId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        performer.setPerformerName(performerName);

        return performer;
    }

    @Override
    public void delete(Integer performerId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(PERFORMER_ID, performerId);
        namedParameterJdbcTemplate.update(DELETE_PERFORMER, parameterSource);
    }

    @Override
    public List<Performer> getFreePerformers() {
        return namedParameterJdbcTemplate.query(GET_FREE_PERFORMERS, new PerformerMapper());
    }
}
