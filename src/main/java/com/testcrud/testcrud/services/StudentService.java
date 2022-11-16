package com.testcrud.testcrud.services;

import com.testcrud.testcrud.entities.Student;
import com.testcrud.testcrud.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public void setIsWorking(Student student, boolean working) {
       student.setWorking(working);
    }

    public void save(Student student) {
        studentRepository.save(student);
    }
}
