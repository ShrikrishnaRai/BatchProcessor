package com.shree.batch.services.person;

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

}
