package com.shree.batch.services.person;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.shree.batch.dao.entity.PersonEntity;
import com.shree.batch.model.Person;
import com.shree.batch.repository.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PersonServiceImplTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonService personService;

    Mapper mapper;

    @BeforeEach
    void setUp() {
        mapper = DozerBeanMapperBuilder.buildDefault();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void listPerson() {
    }

    @Test
    void getPerson() {
    }

    @Test
    void savePersonBulk() {
    }

    void savePerson() {
    }
}