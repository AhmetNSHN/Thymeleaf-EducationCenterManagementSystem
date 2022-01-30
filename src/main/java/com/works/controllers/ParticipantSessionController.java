package com.works.controllers;

import com.works.entities.Participant;
import com.works.entities.SessionParticipant;
import com.works.repositories.SessionParticipantRepository;
import com.works.repositories.SessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(path = "session_participants")

public class ParticipantSessionController {

    private final SessionParticipantRepository sessionParticipantRepository;
    private final SessionRepository sessionRepository;
    private Long sessionId;

    public ParticipantSessionController(SessionParticipantRepository sessionParticipantRepository, SessionRepository sessionRepository) {
        this.sessionParticipantRepository = sessionParticipantRepository;
        this.sessionRepository = sessionRepository;
    }

    @GetMapping(path = "/list")
    public ModelAndView participant(@RequestParam(name = "id") Long id) {
        sessionId = id;

        List<SessionParticipant> spl = sessionParticipantRepository.findAllBySession_Id(id);
        List<Participant> pl = new ArrayList<>();
        for (SessionParticipant sp : spl) {
            pl.add(sp.getParticipant());
        }

        ModelAndView html = new ModelAndView("participantlist");
        html.addObject("participant_list", pl);
        html.addObject("session_info", sessionRepository.findById(id));
        return html;
    }


    @Transactional
    @PostMapping(path = "/delete_participant")
    public ModelAndView deleteProgram(@RequestParam(name = "s_id") long s_id, @RequestParam(name = "p_id") long p_id) {
        sessionParticipantRepository.deleteBySession_IdAndParticipant_Id(sessionId, p_id);
        return new ModelAndView("redirect:list?id=" + sessionId);
    }
}
