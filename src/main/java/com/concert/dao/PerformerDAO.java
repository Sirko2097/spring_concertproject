package com.concert.dao;

import com.concert.model.Performer;

import java.util.List;

public interface PerformerDAO {

    List<Performer> getAllPerformers();

    List<Performer> getAllPerformersFromConcert(Integer concertId);

    Performer create(String performerName);

    void delete(Integer id);

    List<Performer> getFreePerformers();
}
