package com.shree.batch.services.person;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    private final Mapper mapper;

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
        mapper = DozerBeanMapperBuilder.buildDefault();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> listPerson(int page, int limit, String sortBy) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortBy).ascending());
        Page<PersonEntity> personPage = personRepository.findAll(pageable);
        if (personPage.hasContent()) {
            List<PersonEntity> personList = personPage.getContent();
            return personList
                    .stream()
                    .map(personEntity -> mapper.map(personEntity, Person.class)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    @Transactional(readOnly = true)
    public Person getPerson(long personId) {
        Optional<PersonEntity> personOptional = personRepository.findById(personId);
        if (personOptional.isPresent()) {
            return mapper.map(personOptional.get(), Person.class);
        }
        throw new PersonNotFoundException("Person with " + personId + "Not found");
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

    @Override
    @Transactional(readOnly = true)
    public Person findPersonByEmail(String email) {
        Optional<PersonEntity> personEntity = personRepository.findPersonEntitiesByEmail(email);
        if (personEntity.isPresent()) {
            return mapper.map(personRepository.findPersonEntitiesByEmail(email), Person.class);
        }
        throw new PersonNotFoundException("Person with email::" + email + "not found");
    }

    @Override
    @Transactional(readOnly = true)
    public Person findPersonByFirstName(String firstName) {
        Optional<PersonEntity> personEntity = personRepository.findPersonEntitiesByFirstName(firstName);
        if (personEntity.isPresent()) {
            return mapper.map(personRepository.findPersonEntitiesByFirstName(firstName), Person.class);
        }
        throw new PersonNotFoundException("Person with firstName::" + firstName + "not found");
    }

    @Override
    public byte[] downloadPersonDetails(int page, int limit) throws JsonProcessingException {
        List<Person> employees = this.listPerson(page, limit, "");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(employees);
        return json.getBytes();
    }

    @Override
    public Person updateVehicle(long id, Person person) {
        if (personRepository.findById(id).isPresent()) {
            PersonEntity personEntity = personRepository.findById(id).get();
            personEntity.setLastName(person.getLastName());
            personEntity.setFirstName(person.getFirstName());
            personEntity.setEmail(person.getEmail());
            PersonEntity personEntity1 = personRepository.save(personEntity);
            return mapper.map(personEntity1, Person.class);
        }
        throw new PersonNotFoundException("Person with id::" + id + "not found");
    }

}
