package com.works.controllers;

import com.works.entities.Tutor;
import com.works.repositories.TutorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "tutor")
@AllArgsConstructor
public class TutorController {

    private TutorRepository tutorRepository;

    @GetMapping(path = "")
    public ModelAndView tutor(HttpServletRequest request)
    {
        ModelAndView indexHtml = new ModelAndView("tutors");
        indexHtml.addObject("tutor_list", tutorRepository.findAll());
        return indexHtml;
    }

    @GetMapping(path = "add")
    public ModelAndView add()
    {
        ModelAndView indexHtml = new ModelAndView("add_tutor");
        indexHtml.addObject("new_tutor", new Tutor());
        return indexHtml;
    }

    @PostMapping(path = "add_tutor")
    public ModelAndView addTutor(@ModelAttribute(name = "new_tutor") Tutor tutor)
    {
        tutorRepository.save(tutor);
        System.out.println(tutor);
        return new ModelAndView("redirect:/tutor");
    }

    @GetMapping(path = "update")
    public ModelAndView update(@RequestParam(name = "id") long id)
    {
        ModelAndView indexHtml = new ModelAndView("updatetutor");
        Tutor tutor = tutorRepository.findById(id).get();
        indexHtml.addObject("updated_tutor", tutor);
        return indexHtml;
    }

    @PostMapping(path = "updatetutor")
    public ModelAndView updateTutor(@ModelAttribute(name = "updated_tutor") Tutor tutor)
    {
        tutorRepository.save(tutor);
        return new ModelAndView("redirect:/tutor");
    }

    @PostMapping(path = "delete_tutor")
    public ModelAndView deleteTutor(@RequestParam(name = "id") long id)
    {
        tutorRepository.deleteById(id);
        return new ModelAndView("redirect:/tutor");
    }

}
