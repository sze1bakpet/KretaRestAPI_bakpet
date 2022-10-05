package org.vasvari.kreta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.vasvari.kreta.model.Subject;
import org.vasvari.kreta.service.SubjectService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SubjectController {
    @Autowired
    SubjectService service;

    //https://www.baeldung.com/spring-boot-crud-thymeleaf?fbclid=IwAR2czivf1vO9ywPVVRhOXrytgvHRXEuDv0zLD5WOTGPNIl3N3kJ5uKBthtE
    // http://localhost:7777/swagger-ui/index.html
    @GetMapping("/subject/index")
    public String showSubjectList(Model model)
    {
        List<Subject> subjects = service.getAllSubject();
        model.addAttribute("subjects",subjects);
        return "/subjects/index";
    }

    @GetMapping("/subject/edit/{id}")
    public String showUpdateForm (@PathVariable("id") long id, Model model){
        Subject subject = service.getSubjectById(id);
        model.addAttribute("subject", subject);
        return "/subjects/update-subject";
    }

    @PostMapping("/subject/update/{id}")
    public String updateSubject(@PathVariable("id") Long id, @Valid Subject subject, BindingResult result, Model model) {
        if (result.hasErrors()){
            subject.setId(id);
            return "/subjects/update-subject";
        }
        service.saveOrUpdate(subject);
        return "redirect:/subject/index";
    }
    @GetMapping("/subject/delete/{id}")
    public String deleteUser (@PathVariable("id") long id, Model model) {
        Subject subject = service.getSubjectById(id);
        service.delete(id);
        return "redirect:/subject/index";
    }

    @GetMapping ("/subject/signup")
    public ModelAndView showSingUpForm(){
        Subject newSubject=new Subject();
        ModelAndView mav=new ModelAndView();
        mav.setViewName("/subjects/add-subject");
        mav.addObject("subject",newSubject);
        return mav;
    }

    @PostMapping("/subject/add-subject")
    public String addNewSubject(@Valid Subject subject, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/subject/add-subject";
        }
        service.saveOrUpdate(subject);
        return "redirect:/subject/index";
    }
}
