package ru.dvornikov.MySpringBoot2DataBase6.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dvornikov.MySpringBoot2DataBase6.dao.SubjectDAO;
import ru.dvornikov.MySpringBoot2DataBase6.entity.Subject;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectDAO subjectDAO;

    @Override
    @Transactional
    public List<Subject> getAllSubjects() {
        return subjectDAO.getAllSubjects();
    }

    @Override
    @Transactional
    public Subject saveSubject(Subject subject) {
        return subjectDAO.saveSubject(subject);
    }

    @Override
    @Transactional
    public Subject getSubject(int id) {
        return subjectDAO.getSubject(id);
    }

    @Override
    @Transactional
    public void deleteSubject(int id) {
        subjectDAO.deleteSubject(id);
    }
}