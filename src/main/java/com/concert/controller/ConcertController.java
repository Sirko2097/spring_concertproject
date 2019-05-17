package com.concert.controller;

import com.concert.dao.ConcertDAO;
import com.concert.dao.PerformerDAO;
import com.concert.model.Concert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/concerts")
public class ConcertController {

    private ConcertDAO concertDAO;
    private PerformerDAO performerDAO;

    @Autowired
    public void setConcertDAO(ConcertDAO concertDAO, PerformerDAO performerDAO) {
        this.concertDAO = concertDAO;
        this.performerDAO = performerDAO;
    }

    @RequestMapping("/new")
    public String addNewPerformer(Model model) {
        Concert concert = new Concert();
        model.addAttribute("concert", concert);
        model.addAttribute("breaks", concertDAO.getAllBreaks());
        return "new-concert";
    }

    @RequestMapping("/confirm")
    public String confirm(@ModelAttribute("performer") Concert concert, Model model) {
        concertDAO.create(concert.getConcertDate(), concert.getBreakId(), concert.getConcertName(),
                concert.getAnnouncerId());
        model.addAttribute("concerts", concertDAO.getAllConcerts());
        return "concerts";
    }

    @RequestMapping("/all")
    public String getAllConcerts(Model model) {
        model.addAttribute("concerts", concertDAO.getAllConcerts());
        return "concerts";
    }

    @RequestMapping("/all/{concertId}")
    public String getAllInfoAboutConcert(@PathVariable("concertId") Integer concertId, Model model) {
        model.addAttribute("concert", concertDAO.getAllInfoAboutConcert(concertId));
        model.addAttribute("performers", performerDAO.getAllPerformersFromConcert(concertId));
        return "concert-info";
    }

    @RequestMapping("{concertId}/editdate/")
    public String setDateToConcert(@PathVariable("concertId") Integer concertId, Model model) {
        Concert concert = new Concert();
        concert.setConcertId(concertId);
        model.addAttribute("concert", concert);

        return "edit-date";
    }

    @RequestMapping("{concertId}/editdate/confirm")
    public String confirmConcert(@ModelAttribute("concert") Concert concert, @PathVariable("concertId") Integer concertId,
                          Model model) {
        System.out.println(concert.getConcertDate());
        concert.setConcertId(concertId);
        concertDAO.setDate(concert.getConcertId(), concert.getConcertDate());
        model.addAttribute("concerts", concertDAO.getAllConcerts());
        return "concerts";
    }

    @RequestMapping("/delete/{concertId}")
    public String delete(@PathVariable("concertId") Integer concertId, Model model) {
        concertDAO.deleteConcert(concertId);
        model.addAttribute("concerts", concertDAO.getAllConcerts());
        return "concerts";
    }

    @RequestMapping("/{concertId}/addperformer")
    public String getFree(@PathVariable("concertId") Integer concertId, Model model) {
        model.addAttribute("concert", concertId);
        model.addAttribute("free", performerDAO.getFreePerformers());
        return "free-performers";
    }

    @RequestMapping("/{concertId}/addperformer/{performerId}")
    public String addPerformer(@PathVariable("concertId") Integer concertId,
                               @PathVariable("performerId") Integer performerId,
                               Model model) {
        concertDAO.addPerformer(performerId, concertId);
        model.addAttribute("free", performerDAO.getFreePerformers());
        return "free-performers";
    }

}
