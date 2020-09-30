package com.shree.batch.services.person;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.shree.batch.dao.entity.PersonEntity;
import com.shree.batch.exceptions.PersonNotFoundException;
import com.shree.batch.model.Person;
import com.shree.batch.repository.PersonRepository;
import com.shree.batch.utils.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    @Transactional(readOnly = true)
    public List<Person> listPerson(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<PersonEntity> personPage = personRepository.findAll(pageable);
        List<PersonEntity> personList = personPage.getContent();
        return personList
                .stream()
                .map(personEntity -> mapper.map(personEntity, Person.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Person getPerson(long personId) {
        Optional<PersonEntity> personOptional = personRepository.findById(personId);
        if (personOptional.isPresent()) {
            return mapper.map(personOptional.get(), Person.class);
        }
        throw new PersonNotFoundException("Person with " + personId + "Not found");
        //        return mapper.map(personRepository.findById(personId).orElse(null), Person.class);
    }

    @Override
    @Transactional
    public Response savePersonBulk(List<Person> personList) {
        try {
            personRepository.saveAll(personList.stream()
                    .map(person ->
                            mapper.map(person, PersonEntity.class)
                    ).collect(Collectors.toList()));
        } catch (Exception e) {
            return new Response(e.toString());
        }
        return new Response("Saved");
    }

    @Override
    @Transactional
    public Person savePerson(Person person) {
        return mapper.map(personRepository
                .save(mapper.map(person, PersonEntity.class)), Person.class);
    }

    @Override
    @Transactional
    public Response deletePerson(long personId) {
        personRepository.deleteById(personId);
        return new Response("deleted person with id " + personId);
    }

}
