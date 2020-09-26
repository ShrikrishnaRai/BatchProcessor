package com.shree.batch.services.person;

import com.shree.batch.dao.entity.PersonEntity;
import com.shree.batch.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PersonService {
    List<Person> listPerson();

    Person getPerson(long personId);

    String savePersonBulk(List<Person> personList);

    Person savePerson(Person person);

    String deletePerson(long personId);
}
