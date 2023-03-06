package com.group.demchenkotest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group.demchenkotest.data.Message;
import com.group.demchenkotest.data.Person;
import com.group.demchenkotest.dto.BaseResponse;
import com.group.demchenkotest.repo.PersonRepository;
import com.group.demchenkotest.service.PersonService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = standaloneSetup(new PersonController(personService)).build();
        objectMapper = new ObjectMapper();
    }

    @AfterEach
    public void clean() {
        personRepository.deleteAll();
    }


    @Test
    public void itShouldReturnSuccessResponseWhenPersonIsFound() throws Exception {
        // GIVEN
        long personId = 1;
        int age = 23;
        Person person = new Person();
        person.setId(personId);
        person.setFirstName("Dmytro");
        person.setLastName("Demchenko");
        person.setDof(LocalDate.of(1999, 7, 23));

        personRepository.save(person);

        //WHEN
        //THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/person/find/{personId}", personId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(person.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(person.getLastName()))
                .andExpect(jsonPath("$.age").value((age)))
                .andExpect(jsonPath("$.message").value(Message.SUCCESS.toString()));
    }

    @Test
    public void itShouldReturnNotFoundResponseWhenPersonIsNotFound() throws Exception {
        //GIVEN
        long personId = 1;
        BaseResponse expectedResponse = new BaseResponse(Message.NOT_FOUND);
        //WHEN
        //THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/person/find/{personId}", personId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedResponse)));
    }
}