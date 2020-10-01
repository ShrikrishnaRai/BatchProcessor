package com.shree.batch.services.person;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.shree.batch.dao.entity.PersonEntity;
import com.shree.batch.exceptions.PersonNotFoundException;
import com.shree.batch.model.Person;
import com.shree.batch.repository.PersonRepository;
import com.shree.batch.utils.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PersonServiceImplTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonServiceImpl personService;

    Mapper mapper;

    @BeforeEach
    void setUp() {
        mapper = DozerBeanMapperBuilder.buildDefault();
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void listPerson() {
        List<PersonEntity> personList = new ArrayList<>();
        personList.add(new PersonEntity("Klopp", "Jurgen"));
        personList.add(new PersonEntity("Salaah", "Mo"));

        when(personRepository.saveAll(personList)).thenReturn(personList);

        assertEquals(personList, personRepository.saveAll(personList));
    }

    @Test
    void getPerson() {
        PersonEntity person = PersonEntity.builder().firstName("Jurgen").lastName("Klopp").build();
        when(personRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(person));

        Person actualPerson = personService.getPerson(1L);
        assertNotNull(actualPerson);
        assertEquals(mapper.map(person, Person.class), actualPerson);
    }

    @Test
    final void testGetPerson_PersonNotFoundException() {
        when(personRepository.findPersonEntitiesByEmail("john.doe@gmail.com")).thenThrow(PersonNotFoundException.class);
        Assertions.assertThrows(PersonNotFoundException.class, () -> {
            personService.findPersonByEmail("john.doe@gmail.com");
        });
    }

    @Test
    final void testGetPersonByFirstname_PersonNotFoundException() {
        when(personRepository.findPersonEntitiesByFirstName("Fernando")).thenThrow(PersonNotFoundException.class);
        Assertions.assertThrows(PersonNotFoundException.class, () -> {
            personService.findPersonByFirstName("Fernando");
        });
    }

    @Test
    void savePersonBulk() {
        List<PersonEntity> personList = new ArrayList<>();
        personList.add(new PersonEntity("Klopp", "Jurgen"));
        personList.add(new PersonEntity("Salaah", "Mo"));

        when(personRepository.saveAll(personList)).thenReturn(personList);

        assertEquals(personList, personRepository.saveAll(personList));

    }


    @Test
    void savePerson() {
        PersonEntity person = PersonEntity.builder().firstName("Jurgen").lastName("Klopp").build();
        when(personRepository.save(person)).thenReturn(person);

        assertEquals(person, personRepository.save(person));
    }
}