package com.concert.dao.impl;

import com.concert.dao.SongDAO;
import com.concert.dao.mappers.SongMapper;
import com.concert.model.Song;
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
@PropertySource("classpath:sql/songdao.properties")
public class SongDAOImpl implements SongDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${INSERT_NEW_SONG}")
    private String INSERT_NEW_SONG;

    @Value("${GET_ALL_PERFORMER_SONGS}")
    private String GET_ALL_PERFORMER_SONGS;

    @Value("${CHANGE_PERFORMER}")
    private String CHANGE_PERFORMER;

    @Value("${RENAME_SONG}")
    private String RENAME_SONG;

    @Value("${DELETE_SONG}")
    private String DELETE_SONG;

    private static final String SONG_NAME = "songName";
    private static final String PERFORMER_ID = "performerId";
    private static final String SONG_ID = "songId";


    @Autowired
    public void setNamedParameterJdbcTemplate(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Song create(String songName, Integer performerId) {
        Song song = new Song();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(PERFORMER_ID, performerId)
                .addValue(SONG_NAME, songName);
        song.setSongName(songName);
        song.setPerformerId(performerId);
        namedParameterJdbcTemplate.update(INSERT_NEW_SONG, parameterSource, keyHolder, new String[]{"id"});
        song.setSongId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return song;
    }

    @Override
    public void renameSong(String newName, Integer songId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(SONG_NAME, newName)
                .addValue(SONG_ID, songId);
        namedParameterJdbcTemplate.update(RENAME_SONG, parameterSource);
    }

    @Override
    public void changePerformer(Integer performerId, Integer songId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(PERFORMER_ID, parameterSource)
                .addValue(SONG_ID, songId);
        namedParameterJdbcTemplate.update(CHANGE_PERFORMER, parameterSource);
    }

    @Override
    public void delete(Integer songId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(SONG_ID, songId);
        namedParameterJdbcTemplate.update(DELETE_SONG, parameterSource);
    }

    @Override
    public List<Song> getAllPerformerSongs(Integer performerId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(PERFORMER_ID, performerId);
        return namedParameterJdbcTemplate.query(GET_ALL_PERFORMER_SONGS, parameterSource, new SongMapper());
    }
}
