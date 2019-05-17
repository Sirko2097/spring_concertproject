package com.concert.dao.mappers;

import com.concert.model.Performer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PerformerMapper implements RowMapper<Performer> {

    @Override
    public Performer mapRow(ResultSet resultSet, int i) throws SQLException {
        Performer performer = new Performer();
        performer.setPerformerId(resultSet.getInt(1));
        performer.setPerformerName(resultSet.getString(2));
        return performer;
    }
}
