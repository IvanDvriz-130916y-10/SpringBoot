package ru.dvornikov.MySpringBoot2DataBase6.service;

import org.springframework.stereotype.Service;
import ru.dvornikov.MySpringBoot2DataBase6.entity.Subject;

import java.util.List;

@Service
public interface SubjectService {
    List<Subject> getAllSubjects();
    Subject saveSubject(Subject subject);
    Subject getSubject(int id);
    void deleteSubject(int id);
}