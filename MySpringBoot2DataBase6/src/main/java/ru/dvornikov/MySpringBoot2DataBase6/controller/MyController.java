package ru.dvornikov.MySpringBoot2DataBase6.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dvornikov.MySpringBoot2DataBase6.entity.Student;
import ru.dvornikov.MySpringBoot2DataBase6.service.StudentService;

import java.util.List;
@RestController
@RequestMapping("/api")
public class MyController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> allStudents() {
        List<Student> allStudents = studentService.getAllStudents();
        return new ResponseEntity<>(allStudents, HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") int id) {
        Student student = studentService.getStudent(id);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/students")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @PutMapping("/students")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") int id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}