package ru.base.MyUiRestDbService.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.base.MyUiRestDbService.entity.Student;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository <Student, Long> {

}
