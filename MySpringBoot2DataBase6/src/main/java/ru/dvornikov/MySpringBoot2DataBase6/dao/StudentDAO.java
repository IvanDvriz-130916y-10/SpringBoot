package ru.dvornikov.MySpringBoot2DataBase6.dao;

import org.springframework.stereotype.Repository;
import ru.dvornikov.MySpringBoot2DataBase6.entity.Student;

import java.util.List;

@Repository
public interface StudentDAO {

    List<Student> getAllStudent();

    Student saveStudent(Student student);

    Student getStudent(int id);

    void deleteStudent(int id);

}