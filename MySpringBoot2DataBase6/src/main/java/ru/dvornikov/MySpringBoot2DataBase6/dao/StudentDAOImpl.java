package ru.dvornikov.MySpringBoot2DataBase6.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dvornikov.MySpringBoot2DataBase6.entity.Student;
import ru.dvornikov.MySpringBoot2DataBase6.entity.Student;


import java.util.List;

@Slf4j
@Repository
public class StudentDAOImpl implements StudentDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Student> getAllStudent() {
        Query query = entityManager.createQuery("from Student");
        List<Student> allSudents = query.getResultList();
        log.info("getAllStudent" + allSudents);
        return allSudents;
    }

    @Override
    public Student saveStudent(Student student) {
        return entityManager.merge(student);
    }

    @Override
    public Student getStudent(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public void deleteStudent(int id) {
        Query query = entityManager.createQuery("delete from Student "
                + " where id =:studentId");
        query.setParameter("studentID", id);
        query.executeUpdate();

    }
}