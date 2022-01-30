package com.works.controllers;

import com.works.entities.Participant;
import com.works.entities.SessionParticipant;
import com.works.repositories.ParticipantRepository;
import com.works.repositories.SessionParticipantRepository;
import com.works.repositories.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(path = "participants")
@AllArgsConstructor
public class ParticipantController {

    private ParticipantRepository participantRepository;
    private SessionRepository sessionRepository;
    private SessionParticipantRepository sessionParticipantRepository;;

    @GetMapping(path = "")
    public ModelAndView participant(HttpServletRequest request)
    {
        ModelAndView indexHtml = new ModelAndView("participants");
        indexHtml.addObject("participant_list", participantRepository.findAll());
        return indexHtml;
    }

    @GetMapping(path = "add")
    public ModelAndView add()
    {
        ModelAndView indexHtml = new ModelAndView("registerparticipant");
        indexHtml.addObject("new_participant", new SessionParticipant());
        indexHtml.addObject("session_list", sessionRepository.findAll());
        return indexHtml;
    }

    @PostMapping(path = "add_participant")
    public ModelAndView addParticipant(@ModelAttribute(name = "new_participant") SessionParticipant sessionParticipant)
    {

        System.out.println(sessionParticipant.getSession());
        participantRepository.save(sessionParticipant.getParticipant());
        sessionParticipantRepository.save(sessionParticipant);
        return new ModelAndView("redirect:/participants");
    }

    @GetMapping(path = "update")
    public ModelAndView update(@RequestParam(name = "id") long id)
    {
        ModelAndView indexHtml = new ModelAndView("updateparticipant");
        Participant participant = participantRepository.findById(id).get();
        indexHtml.addObject("updated_participant", participant);
        return indexHtml;
    }

    @PostMapping(path = "updateparticipant/{}")
    public ModelAndView updateTutor(@ModelAttribute(name = "updated_participant") Participant participant)
    {
        participantRepository.saveAndFlush(participant);
        return new ModelAndView("redirect:/participants");
    }

    @PostMapping(path = "delete_participant")
    public ModelAndView deleteParticipant(@RequestParam(name = "id") long id)
    {
        participantRepository.deleteById(id);
        return new ModelAndView("redirect:/participants");
    }

}
