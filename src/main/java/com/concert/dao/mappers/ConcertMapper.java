package com.concert.dao.mappers;

import com.concert.model.Concert;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConcertMapper implements RowMapper<Concert> {

    @Override
    public Concert mapRow(ResultSet rs, int rowNum) throws SQLException {
        Concert concert = new Concert();
        concert.setConcertId(rs.getInt(1));
        concert.setConcertDate(rs.getDate(2).toLocalDate().toString());
        concert.setBreakId(rs.getInt(3));
        concert.setConcertName(rs.getString(4));
        return concert;
    }
}
