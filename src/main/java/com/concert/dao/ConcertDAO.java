package com.concert.dao;

import com.concert.model.AllInfoAboutPerformance;
import com.concert.model.Break;
import com.concert.model.Concert;

import java.time.LocalDate;
import java.util.List;

public interface ConcertDAO {

    Concert create(LocalDate concertDate, Integer breakId, String concertName, Integer announcerId);

    void setBreak(Integer concertId, Integer breakId);

    void setDate(Integer concertId, LocalDate concertDate);

    AllInfoAboutPerformance getAllInfoAboutConcert(Integer concertId);

    List<Break> getAllBreaks();

    List<Concert> getAllConcerts();

    void deleteConcert(Integer concertId);

    void addPerformer(Integer performerId, Integer concertId);
}
