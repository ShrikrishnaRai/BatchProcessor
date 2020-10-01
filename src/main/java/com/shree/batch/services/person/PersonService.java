package com.shree.batch.services.person;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shree.batch.dao.entity.PersonEntity;
import com.shree.batch.model.Person;
import com.shree.batch.utils.Response;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PersonService {
    List<Person> listPerson(int page, int limit);

    Person getPerson(long personId);

    Response savePersonBulk(List<Person> personList);

    Person savePerson(Person person);

    Response deletePerson(long personId);

    Person findPersonByEmail(String email);

    Person findPersonByFirstName(String firstName);

    byte[] downloadPersonDetails(int page, int limit) throws JsonProcessingException;

    Person updateVehicle(long id, Person person);
}
