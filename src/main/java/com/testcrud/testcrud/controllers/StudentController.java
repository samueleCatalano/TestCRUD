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

    @PostMapping("")
    @ResponseBody
    public ResponseEntity createStudent(@RequestBody Student student) {
        studentService.save(student);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("")
    public @ResponseBody List<Student> getAllStudents(Student student) {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Optional<Student> getSingleStudent(@PathVariable Long id, Student student) {
        return studentService.findById(id);
    }

    @PutMapping("/{id}")
    public void updateIdOfStudent(@PathVariable Long id, @RequestBody @NotNull Student student) {
        student.setId(id);
        studentService.save(student);
    }

    @PutMapping("/{id}/isWorking")
    public void updateIsWorkingValue(@PathVariable Long id, @RequestParam("isWorking") boolean isWorking) {
        Student student = studentService.getReferenceById(id);
        studentService.setIsWorking(student,isWorking);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable Long id) {
        studentService.deleteById(id);
    }
}
