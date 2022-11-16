package com.testcrud.testcrud;

import com.testcrud.testcrud.entities.Student;
import com.testcrud.testcrud.services.StudentService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles(value = "test")
@Transactional
public class StudentServiceTest{

    @Autowired
    StudentService studentService;


    @Test
    public void contextLoad() {
        assertThat(studentService).isNotNull();
    }

    @Test
    public void setIsWorkingTest(){
        Student student = new Student();
        student.setId(1L);
        student.setName("Samuele");
        student.setSurname("Catalano");
        student.setWorking(true);

        studentService.setIsWorking(student,false);
        studentService.save(student);

      assertThat(student.isWorking()).isFalse();
    }
}
