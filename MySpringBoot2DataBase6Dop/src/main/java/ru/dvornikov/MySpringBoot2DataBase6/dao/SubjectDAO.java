package ru.dvornikov.MySpringBoot2DataBase6.dao;

import org.springframework.stereotype.Repository;
import ru.dvornikov.MySpringBoot2DataBase6.entity.Subject;

import java.util.List;

@Repository
public interface SubjectDAO {
    List<Subject> getAllSubjects();
    Subject saveSubject(Subject subject);
    Subject getSubject(int id);
    void deleteSubject(int id);
}