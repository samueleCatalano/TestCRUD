package com.testcrud.testcrud.services;

import com.testcrud.testcrud.entities.Student;
import com.testcrud.testcrud.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        return studentList;
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public Student getReferenceById(Long id) {
        return studentRepository.getReferenceById(id);
    }

    public void deleteById(Long id) {
    }
}
