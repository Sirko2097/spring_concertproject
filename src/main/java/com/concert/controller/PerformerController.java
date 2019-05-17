package com.concert.controller;

import com.concert.dao.PerformerDAO;
import com.concert.model.Performer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/performers")
public class PerformerController {

    private PerformerDAO performerDAO;

    @Autowired
    public void setPerformerDAO(PerformerDAO performerDAO) {
        this.performerDAO = performerDAO;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String allPerformers(Model model) {
        List<Performer> allPerformers = performerDAO.getAllPerformers();
        model.addAttribute("performers", allPerformers);
        return "performers";
    }

    @RequestMapping("/all/free")
    public String freePerformers(Model model) {
        List<Performer> allPerformers = performerDAO.getFreePerformers();
        model.addAttribute("performers", allPerformers);
        return "performers";
    }

    @RequestMapping("/all/{concertId}")
    public String allPerformersFromConcert(@PathVariable("concertId") Integer concertId, Model model) {
        List<Performer> fromConcert = performerDAO.getAllPerformersFromConcert(concertId);
        model.addAttribute("performers", fromConcert);
        return "performers-from-concert";
    }

    @RequestMapping("/new")
    public String addNewPerformer(Model model) {
        Performer performer = new Performer();
        model.addAttribute("performer", performer);

        return "new-performer";
    }

    @RequestMapping("/confirm")
    public String confirm(@ModelAttribute("performer") Performer performer, Model model) {
        performerDAO.create(performer.getPerformerName());
        model.addAttribute("performers", performerDAO.getAllPerformers());
        return "performers";
    }

    @RequestMapping("/delete/{performerId}")
    public String delete(@PathVariable("performerId") Integer performerId, Model model) {
        performerDAO.delete(performerId);
        model.addAttribute("performers", performerDAO.getAllPerformers());
        return "performers";
    }

}
