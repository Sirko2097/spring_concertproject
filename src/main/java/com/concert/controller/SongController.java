package com.concert.controller;

import com.concert.dao.SongDAO;
import com.concert.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {

    private SongDAO songDAO;

    @Autowired
    public void setSongDAO(SongDAO songDAO) {
        this.songDAO = songDAO;
    }

    @RequestMapping("/all/{performerId}")
    public String getAllPerformersSongs(@PathVariable("performerId") Integer performerId, Model model) {
        List<Song> allPerformerSongs = songDAO.getAllPerformerSongs(performerId);
        model.addAttribute("songs", allPerformerSongs);
        return "songs";
    }

    @RequestMapping("/{performerId}/new")
    public String addNewSong(@PathVariable("performerId") Integer performerId, Model model) {
        Song song = new Song();
        song.setPerformerId(performerId);
        model.addAttribute("song", song);
        return "new-song";
    }

    @RequestMapping("/{performerId}/new/confirm")
    public String confirmSong(@ModelAttribute("song") Song song, @PathVariable("performerId") Integer performerId,
                              Model model) {
        songDAO.create(song.getSongName(), performerId);
        model.addAttribute("songs", songDAO.getAllPerformerSongs(song.getPerformerId()));

        return "songs";
    }

    @RequestMapping("{performerId}/delete/{songId}")
    public String delete(@PathVariable("songId") Integer songId, @PathVariable("performerId") Integer performerId, Model model) {
        songDAO.delete(songId);
        model.addAttribute("songs", songDAO.getAllPerformerSongs(performerId));
        return "songs";
    }
}
