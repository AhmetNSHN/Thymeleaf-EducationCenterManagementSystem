package com.works.controllers;

import com.works.entities.Program;
import com.works.entities.Session;
import com.works.repositories.ProgramRepository;
import com.works.repositories.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "program")
@AllArgsConstructor
public class ProgramController {

    private ProgramRepository programRepository;

    @GetMapping(path = "")
    public ModelAndView program(HttpServletRequest request)
    {
        ModelAndView indexHtml = new ModelAndView("programs");
        indexHtml.addObject("program_list", programRepository.findAll());
        return indexHtml;
    }

    @GetMapping(path = "add")
    public ModelAndView add()
    {
        ModelAndView indexHtml = new ModelAndView("create_program");
        indexHtml.addObject("new_program", new Program());
        return indexHtml;
    }

    @PostMapping(path = "add_program")
    public ModelAndView addProgram(@ModelAttribute(name = "new_program") Program program)
    {
        programRepository.save(program);
        System.out.println(program);
        return new ModelAndView("redirect:/program");
    }

    @GetMapping(path = "update")
    public ModelAndView update(@RequestParam(name = "id") long id)
    {
        ModelAndView indexHtml = new ModelAndView("updateprogram");
        Program program = programRepository.findById(id).get();
        indexHtml.addObject("updated_program", program);
        return indexHtml;
    }

    @PostMapping(path = "updateprogram")
    public ModelAndView updateProgram(@ModelAttribute(name = "updated_program") Program program)
    {
        programRepository.save(program);
        return new ModelAndView("redirect:/program");
    }

    @PostMapping(path = "delete_program")
    public ModelAndView deleteProgram(@RequestParam(name = "id") long id)
    {
        programRepository.deleteById(id);
        return new ModelAndView("redirect:/program");
    }
}
