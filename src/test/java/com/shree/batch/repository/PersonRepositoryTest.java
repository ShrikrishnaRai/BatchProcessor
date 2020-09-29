package com.shree.batch.repository;


import static org.assertj.core.api.Assertions.assertThat;

import com.shree.batch.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void savePersonTest() {

    }
}