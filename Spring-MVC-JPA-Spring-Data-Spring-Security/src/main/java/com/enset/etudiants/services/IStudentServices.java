package com.enset.etudiants.services;

import com.enset.etudiants.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStudentServices {
    /*Student functions*/
    List<Student> studentsList();
    Student saveStudent(Student student);
    void deleteStudent(Long id);
    Student findStudent(Long id);
    Page<Student> findStudentBykeyword(String kw,Pageable pageable);
}
