package org.vasvari.kreta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vasvari.kreta.model.Subject;
import org.vasvari.kreta.service.SubjectService;

import java.util.List;

@RestController
public class SubjectAPIController {
    @Autowired
    SubjectService service;

    @GetMapping(value = "/api/subject",name = "List of all subjects.")
    public List<Subject> getAllSubjects() {
        return service.getAllSubject();
    }

    @PostMapping(value = "/api/subject",name = "Save subject subject")
    public long saveSubject(@RequestBody Subject subject) {
        service.saveOrUpdate(subject);
        return subject.getId();
    }

    @PutMapping(value = "/api/subject",name="Update subject")
    private Subject update(@RequestBody Subject subject) {
        service.saveOrUpdate(subject);
        return subject;
    }

    @DeleteMapping(value="/api/subject/{subjectid}",name = "Delete subject")
    private void deleteSubject(@PathVariable("subjectid") Long subjectid) {
        service.delete(subjectid);
    }


}
