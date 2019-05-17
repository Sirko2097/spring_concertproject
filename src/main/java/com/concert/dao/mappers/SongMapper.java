package com.concert.dao.mappers;

import com.concert.model.Song;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SongMapper implements RowMapper<Song> {

    @Override
    public Song mapRow(ResultSet rs, int rowNum) throws SQLException {
        Song song = new Song();
        song.setSongId(rs.getInt(1));
        song.setSongName(rs.getString(2));
        song.setPerformerId(rs.getInt(3));
        return song;
    }
}
