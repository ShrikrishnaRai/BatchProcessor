package com.shree.batch.resources;

import com.shree.batch.dao.entity.PersonEntity;
import com.shree.batch.model.Person;
import com.shree.batch.repository.PersonRepository;
import com.shree.batch.services.person.PersonService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonResourceTest {

    @MockBean
    private PersonService personService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }



    @Test
    void getPersonList() {
    }

    @Test
    void savePersonBulk() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Sallah", "Mo"));
        personList.add(new Person("mane", "Sadio"));
//        ResponseEntity<List<Person>> personResponse = testRestTemplate.postForEntity("/person/list",
//                personList,
//                String.class
//        );

    }

    @Test
    void savePerson() {
        ResponseEntity<Person> personResponse = testRestTemplate.postForEntity("/person/",
                Person
                        .builder()
                        .firstName("Jurgen")
                        .lastName("Klopp")
                        .build(),
                Person.class);
        assertThat(personResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void deletePersonById() {
    }


    @Test
    void getPersonById() {
        //when
        ResponseEntity<Person> personResponse = testRestTemplate.getForEntity("/person/1", Person.class);

        //then
        assertThat(personResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.equals(personResponse.getBody(), new Person("JILL", "DOE")));
    }
}