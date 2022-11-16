
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testcrud.testcrud.controllers.StudentController;
import com.testcrud.testcrud.entities.Student;
import com.testcrud.testcrud.services.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = StudentController.class)
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private StudentController studentController;

    private StudentService studentService;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Autowired
    private MockMvc mockMvc;

    private Student createAStudent() throws Exception {
        Student student = new Student();
        student.setName("Samuele");
        student.setSurname("Catalano");
        student.setWorking(true);

        return student;


    }
    private String createAStudentAsAJSON() throws Exception {

        Student student = createAStudent();

        String studentJSON = objectMapper.writeValueAsString(student);

        return studentJSON;
    }
    private String createAStudentWithMockMvc() throws Exception {

        String student = createAStudentAsAJSON();

        String studentJSON = objectMapper.writeValueAsString(student);

        MvcResult result = mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJSON)).andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        return studentJSON;
    }

    @Test
  public void contextLoad(){
        assertThat(studentController).isNotNull();
    }

    @Test
    public void createTest() throws Exception {
        String studentJSON = createAStudentAsAJSON();

        MvcResult result = mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJSON)).andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Student studentresult = objectMapper.readValue(result.getResponse().getContentAsString(),Student.class);
        assertThat(studentresult).isNotNull();
    }

    @Test
    public void GetAListTest() throws Exception {

        createAStudent();

        MvcResult result = mockMvc.perform(get("/student"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        List<Student> studentListResult = objectMapper.readValue(result.getResponse().getContentAsString(),List.class);
        assertThat(studentListResult.size()).isNotZero();
    }

    @Test
    public void GetASingleTest() throws Exception {

        createAStudentWithMockMvc();

        MvcResult result = mockMvc.perform(get("/student{"+createAStudent().getId()+"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Student studentResult = objectMapper.readValue(result.getResponse().getContentAsString(),Student.class);
        assertThat(studentResult.getId()).isEqualTo(createAStudent().getId());
    }

    @Test
    public void updateTest() throws Exception {

        String studentJSON = createAStudentWithMockMvc();

        MvcResult result = mockMvc.perform(put("/student{"+createAStudent().getId()+"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Student studentResult = objectMapper.readValue(result.getResponse().getContentAsString(),Student.class);
        assertThat(studentResult.getId()).isEqualTo(createAStudent().getId());
    }

    @Test
    public void deleteTest() throws Exception {

        String studentJSON = createAStudentWithMockMvc();

        MvcResult result = mockMvc.perform(delete("/student{"+createAStudent().getId()+"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Student studentResult = objectMapper.readValue(result.getResponse().getContentAsString(),Student.class);
        assertThat(studentResult).isNull();
    }






}