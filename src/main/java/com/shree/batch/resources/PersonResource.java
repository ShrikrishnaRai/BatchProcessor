package com.shree.batch.resources;

import com.shree.batch.model.Person;
import com.shree.batch.services.person.PersonService;
import com.shree.batch.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class PersonResource {

    @Autowired
    private PersonService personService;


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
    public ResponseEntity<Response> savePersonBulk(@RequestBody List<Person> personList) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personService.savePersonBulk(personList));
    }

    @PostMapping
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personService.savePerson(person));
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<Response> deletePersonById(@PathVariable long personId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(personService.deletePerson(personId));
    }
}
