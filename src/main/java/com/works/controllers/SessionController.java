package com.works.controllers;

import com.works.entities.Session;
import com.works.repositories.ProgramRepository;
import com.works.repositories.SessionRepository;
import com.works.repositories.TutorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "sessions")
@AllArgsConstructor
public class SessionController {
    private SessionRepository sessionRepository;
    private ProgramRepository programRepository;
    private TutorRepository tutorRepository;

    @GetMapping(path = "")
    public ModelAndView session(HttpServletRequest request)
    {
        ModelAndView indexHtml = new ModelAndView("sessions");
        indexHtml.addObject("session_list", sessionRepository.findAll());
        return indexHtml;
    }

    @GetMapping(path = "add")
    public ModelAndView add()
    {
        ModelAndView html = new ModelAndView("create_session");
        html.addObject("session", new Session());
        html.addObject("program_list", programRepository.findAll());
        html.addObject("tutor_list", tutorRepository.findAll());
        return html;
    }

    @PostMapping(path = "add_session")
    public ModelAndView addProgram(@ModelAttribute(name = "new_session") Session session)
    {
        System.out.println(session.getProgram());
        sessionRepository.save(session);
        System.out.println(session);
        return new ModelAndView("redirect:/sessions");
    }

    @GetMapping(path = "update")
    public ModelAndView update(@RequestParam(name = "id") long id)
    {
        ModelAndView indexHtml = new ModelAndView("updatesession");
        Session session = sessionRepository.findById(id).get();
        indexHtml.addObject("tutor_list", tutorRepository.findAll());
        indexHtml.addObject("program_list", programRepository.findAll());
        indexHtml.addObject("session", session);
        return indexHtml;
    }

    @PostMapping(path = "updatesession")
    public ModelAndView updateProgram(@ModelAttribute(name = "updated_session") Session session)
    {
        sessionRepository.save(session);
        return new ModelAndView("redirect:/sessions");
    }

    @PostMapping(path = "delete_session")
    public ModelAndView deleteProgram(@RequestParam(name = "id") long id)
    {
        sessionRepository.deleteById(id);
        return new ModelAndView("redirect:/sessions");
    }

}
