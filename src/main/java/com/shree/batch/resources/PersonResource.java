package com.shree.batch.resources;

import com.shree.batch.model.Person;
import com.shree.batch.services.person.PersonService;
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

    @GetMapping("/{personId}")
    public ResponseEntity<Person> getPersonById(@PathVariable long personId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personService.getPerson(personId));
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
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personService.savePerson(person));
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<String> deletePersonById(@PathVariable long personId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(personService.deletePerson(personId));
    }
}
