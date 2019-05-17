package com.concert.dao;

import com.concert.model.Song;

import java.util.List;

public interface SongDAO {

    Song create(String songName, Integer performerId);

    void renameSong(String newName, Integer songId);

    void changePerformer(Integer performerId, Integer songId);

    void delete(Integer songId);

    List<Song> getAllPerformerSongs(Integer performerId);
}
