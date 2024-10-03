package ru.dvornikov.MySpringBoot2DataBase6.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dvornikov.MySpringBoot2DataBase6.entity.Subject;

import java.util.List;

@Repository
public class SubjectDAOImpl implements SubjectDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Subject> getAllSubjects() {
        Query query = entityManager.createQuery("from Subject");
        return query.getResultList();
    }

    @Override
    public Subject saveSubject(Subject subject) {
        return entityManager.merge(subject);
    }

    @Override
    public Subject getSubject(int id) {
        return entityManager.find(Subject.class, id);
    }

    @Override
    public void deleteSubject(int id) {
        Query query = entityManager.createQuery("delete from Subject where id =:subjectId");
        query.setParameter("subjectId", id);
        query.executeUpdate();
    }
}