package ru.dvornikov.MySpringBoot2DataBase6.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dvornikov.MySpringBoot2DataBase6.dao.StudentDAO;
import ru.dvornikov.MySpringBoot2DataBase6.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dvornikov.MySpringBoot2DataBase6.entity.Student;
import ru.dvornikov.MySpringBoot2DataBase6.dao.StudentDAO;

import java.util.List;

import java.util.List;

@Service
public class StudentServiceImpl  implements StudentService{
    @Autowired
    private StudentDAO studentDAO;

    @Override
    @Transactional
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudent();
    }
    @Override
    @Transactional
    public Student saveStudent(Student student) {
        return studentDAO.saveStudent(student);
    }
    @Override
    @Transactional
    public Student getStudent(int id) {
        return studentDAO.getStudent(id);
    }

    @Override
    @Transactional
    public void deleteStudent(int id) {
        studentDAO.deleteStudent(id);
    }
}
