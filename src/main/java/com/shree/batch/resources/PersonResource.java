package com.shree.batch.resources;

import com.shree.batch.dao.entity.PersonEntity;
import com.shree.batch.model.Person;
import com.shree.batch.services.person.PersonService;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonResource {

    private final PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personService.getPerson(id));
    }

    @GetMapping
    public ResponseEntity<List<Person>> getPersonList() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personService.listPerson());
    }

    @PostMapping("/list")
    public ResponseEntity<String> savePersonBulk(@RequestBody List<Person> personList) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personService.savePersonBulk(personList));
    }

    @PostMapping
    public ResponseEntity<String> savePerson(@RequestBody Person person) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(personService.savePerson(person));
    }
}
