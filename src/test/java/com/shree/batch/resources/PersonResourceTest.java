package com.shree.batch.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shree.batch.model.Person;
import com.shree.batch.services.person.PersonService;
import com.shree.batch.utils.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class PersonResourceTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPersonById() throws Exception {
        Person person = new Person("Klopp", "Jurgen");
        when(personService.getPerson(1L)).thenReturn(person);

        ObjectMapper objectMapper = new ObjectMapper();
        String personJson = objectMapper.writeValueAsString(person);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/person/1")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(personJson));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jurgen"))
                .andExpect(jsonPath("$.lastName").value("Klopp"));
    }

    @Test
    void getPersonList() throws Exception {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Klopp", "Jurgen"));
        personList.add(new Person("Salaah", "Mo"));

        when(personService.listPerson(1, 25,"")).thenReturn(personList);

        mockMvc.perform(MockMvcRequestBuilders.get("/person")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2))).andDo(print());
    }

    @Test
    void savePersonBulk() throws Exception {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Klopp", "Jurgen"));
        personList.add(new Person("Salaah", "Mo"));

        ObjectMapper objectMapper = new ObjectMapper();
        String personJson = objectMapper.writeValueAsString(personList);


        when(personService.savePersonBulk(personList)).thenReturn(new Response("Saved"));

        ResultActions resultActions = mockMvc.perform(post("/person/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(personJson));

        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Saved"));

    }

    @Test
    void savePerson() throws Exception {
        Person person = new Person("Klopp", "Jurgen");
        when(personService.savePerson(any(Person.class))).thenReturn(person);

        ObjectMapper objectMapper = new ObjectMapper();
        String personJson = objectMapper.writeValueAsString(person);

        ResultActions resultActions = mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(personJson));

        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Jurgen"))
                .andExpect(jsonPath("$.lastName").value("Klopp"));

    }

    @Test
    void deletePersonById() throws Exception {
        when(personService.deletePerson(1L)).thenReturn(new Response("deleted person with id " + 1));

        ResultActions resultActions = mockMvc.perform(delete("/person/1"));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("deleted person with id " + 1));
    }
}