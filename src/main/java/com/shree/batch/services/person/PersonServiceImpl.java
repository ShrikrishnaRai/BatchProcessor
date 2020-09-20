package com.shree.batch.services.person;

import com.github.dozermapper.core.DozerBeanMapper;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.shree.batch.dao.entity.PersonEntity;
import com.shree.batch.model.Person;
import com.shree.batch.repository.PersonRepository;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    Mapper mapper;

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
        mapper = DozerBeanMapperBuilder.buildDefault();
    }

    @Override
    public List<Person> listPerson() {
        return personRepository
                .findAll()
                .stream()
                .map(personEntity ->
                        mapper.map(personEntity, Person.class)
                )
                .collect(Collectors.toList());
    }

    @Override
    public Person getPerson(long personId) {
        return mapper.map(personRepository.getOne(personId), Person.class);
    }

    @Override
    public String savePersonBulk(List<Person> personList) {
        try {
            personRepository.saveAll(personList.stream()
                    .map(person ->
                            mapper.map(person, PersonEntity.class)
                    ).collect(Collectors.toList()));
        } catch (Exception e) {
            return "" + e;
        }
        return "saved";
    }

    @Override
    public String savePerson(Person person) {
        return personRepository.save(mapper.map(person, PersonEntity.class)).getFirstName() + " detail's saved";
    }
}
