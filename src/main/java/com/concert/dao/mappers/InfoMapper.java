package com.concert.dao.mappers;

import com.concert.model.AllInfoAboutPerformance;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InfoMapper implements RowMapper<AllInfoAboutPerformance> {

    @Override
    public AllInfoAboutPerformance mapRow(ResultSet rs, int rowNum) throws SQLException {
        AllInfoAboutPerformance allInfoAboutPerformance = new AllInfoAboutPerformance();
        allInfoAboutPerformance.setConcertId(rs.getInt(1));
        allInfoAboutPerformance.setConcertName(rs.getString(2));
        allInfoAboutPerformance.setConcertDate(rs.getDate(3).toLocalDate());
        allInfoAboutPerformance.setBreakName(rs.getString(4));
        allInfoAboutPerformance.setAnnouncerName(rs.getString(5));
        return allInfoAboutPerformance;
    }
}
