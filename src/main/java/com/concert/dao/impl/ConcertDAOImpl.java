package com.concert.dao.impl;

import com.concert.dao.ConcertDAO;
import com.concert.dao.mappers.ConcertMapper;
import com.concert.dao.mappers.InfoMapper;
import com.concert.model.AllInfoAboutPerformance;
import com.concert.model.Break;
import com.concert.model.Concert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Repository
@PropertySource("classpath:sql/concertdao.properties")
public class ConcertDAOImpl implements ConcertDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${INSERT_NEW_CONCERT}")
    private String INSERT_NEW_CONCERT;

    @Value("${SET_BREAK}")
    private String SET_BREAK;

    @Value("${SET_DATE}")
    private String SET_DATE;

    @Value("${GET_INFO_ABOUT_CONCERT}")
    private String GET_INFO_ABOUT_CONCERT;

    @Value("${ALL_INFO_ABOUT_CONCERT}")
    private String ALL_INFO_ABOUT_CONCERT;

    @Value("${GET_ALL_BREAKS}")
    private String GET_ALL_BREAKS;

    @Value("${DELETE_CONCERT}")
    private String DELETE_CONCERT;

    @Value("${INSERT_NEW_PERFORMER_TO_CONCERT}")
    private String INSERT_NEW_PERFORMER_TO_CONCERT;

    @Value("${INSERT_NEW_ANNOUNCER_TO_CONCERT}")
    private String INSERT_NEW_ANNOUNCER_TO_CONCERT;

    private static final String CONCERT_DATE = "concertDate";
    private static final String BREAK_ID = "breakId";
    private static final String CONCERT_NAME = "concertName";
    private static final String CONCERT_ID = "concertId";
    private static final String PERFORMER_ID = "performerId";
    private static final String ANNOUNCER_ID = "announcerId";

    @Autowired
    public void setNamedParameterJdbcTemplate(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Concert create(LocalDate concertDate, Integer breakId, String concertName, Integer announcerId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(CONCERT_DATE, concertDate)
                .addValue(BREAK_ID, breakId)
                .addValue(CONCERT_NAME, concertName);
        namedParameterJdbcTemplate.update(INSERT_NEW_CONCERT, parameterSource, keyHolder, new String[]{"id"});
        parameterSource.addValue(CONCERT_ID, Objects.requireNonNull(keyHolder.getKey()).intValue())
                .addValue(ANNOUNCER_ID, announcerId);
        namedParameterJdbcTemplate.update(INSERT_NEW_ANNOUNCER_TO_CONCERT, parameterSource);

        Concert concert = new Concert();
        concert.setConcertId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        concert.setConcertDate(concertDate.toString());
        concert.setConcertName(concertName);
        concert.setBreakId(breakId);
        concert.setAnnouncerId(announcerId);

        return concert;
    }

    @Override
    public void setBreak(Integer concertId, Integer breakId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(CONCERT_ID, concertId)
                .addValue(BREAK_ID, breakId);
        namedParameterJdbcTemplate.update(SET_BREAK, parameterSource);
    }

    @Override
    public void setDate(Integer concertId, LocalDate concertDate) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(CONCERT_ID, concertId)
                .addValue(CONCERT_DATE, concertDate);
        namedParameterJdbcTemplate.update(SET_DATE, parameterSource);
    }

    @Override
    public AllInfoAboutPerformance getAllInfoAboutConcert(Integer concertId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(CONCERT_ID, concertId);
        return namedParameterJdbcTemplate.queryForObject(ALL_INFO_ABOUT_CONCERT, parameterSource, new InfoMapper());
    }

    @Override
    public List<Break> getAllBreaks() {
        return namedParameterJdbcTemplate.query(GET_ALL_BREAKS, new BeanPropertyRowMapper<>(Break.class));
    }

    @Override
    public void deleteConcert(Integer concertId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(CONCERT_ID, concertId);
        namedParameterJdbcTemplate.update(DELETE_CONCERT, parameterSource);
    }

    @Override
    public List<Concert> getAllConcerts() {
        return namedParameterJdbcTemplate.query(GET_INFO_ABOUT_CONCERT, new ConcertMapper());
    }

    @Override
    public void addPerformer(Integer performerId, Integer courseId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(PERFORMER_ID, performerId)
                .addValue(CONCERT_ID, courseId);
        namedParameterJdbcTemplate.update(INSERT_NEW_PERFORMER_TO_CONCERT, parameterSource);
    }
}
