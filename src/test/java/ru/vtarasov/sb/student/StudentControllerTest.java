package ru.vtarasov.sb.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author vtarasov
 * @since 27.09.2019
 */
@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private StudentRegistrationService studentRegistrationService;

    @Autowired
    private ObjectMapper objectMapper;

    private Student student;

    @Before
    public void setUp() {
        student = new Student("Registered", 18);
        student.setId("id-registered");

        Mockito.when(studentRegistrationService.find("id-registered")).thenReturn(Optional.of(student));
        Mockito.when(studentRegistrationService.find("id-not-registered")).thenReturn(Optional.ofNullable(null));
    }

    @Test
    public void shouldNotFoundStudentIfNotRegistered() throws Exception {
        mvc
            .perform(MockMvcRequestBuilders.get("/student/{id}", "id-not-registered"))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void shouldFoundStudentIfRegistered() throws Exception {
        mvc
            .perform(MockMvcRequestBuilders.get("/student/{id}", "id-registered"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(student)));
    }
}
