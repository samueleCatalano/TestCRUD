package com.testcrud.testcrud.controllers;

import com.sun.istack.NotNull;
import com.testcrud.testcrud.entities.Student;
import com.testcrud.testcrud.repositories.StudentRepository;
import com.testcrud.testcrud.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    StudentService studentService;
    StudentRepository repository;

    @PostMapping("")
    @ResponseBody
    public ResponseEntity createStudent(@RequestBody Student student) {
        repository.save(student);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("")
    public @ResponseBody List<Student> getAllStudents(Student student) {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Optional<Student> getSingleStudent(@PathVariable Long id, Student student) {
        return repository.findById(id);
    }

    @PutMapping("/{id}")
    public void updateIdOfStudent(@PathVariable Long id, @RequestBody @NotNull Student student) {
        student.setId(id);
        repository.save(student);
    }

    @PutMapping("/{id}/isWorking")
    public void updateIsWorkingValue(@PathVariable Long id, @RequestParam("isWorking") boolean isWorking) {
        Student student = repository.getReferenceById(id);
        studentService.setIsWorking(student,isWorking);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
