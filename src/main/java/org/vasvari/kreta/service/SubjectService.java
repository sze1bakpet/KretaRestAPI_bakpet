package org.vasvari.kreta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vasvari.kreta.model.Subject;
import org.vasvari.kreta.repo.interfaces.SubjectRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    public List<Subject> getAllSubject() {
        List<Subject> subjects=new ArrayList<>();
        subjectRepository.findAll()
                .forEach(subject -> subjects.add(subject));
        return subjects;
    }

    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id).get();
    }

    public void saveOrUpdate(Subject subject) {
        subjectRepository.save(subject);
    }

    public void delete(Long id) {
        subjectRepository.deleteById(id);
    }

    public void update(Subject subject, Long id) {
        subjectRepository.save(subject);
    }
}
