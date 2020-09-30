package com.shree.batch.resources;

import com.shree.batch.model.Person;
import com.shree.batch.services.person.PersonService;
import com.shree.batch.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/person")
public class PersonResource {

    @Autowired
    private PersonService personService;


    @GetMapping("/{personId}")
    public ResponseEntity<Person> getPersonById(@PathVariable long personId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personService.getPerson(personId)
                        .add(linkTo(methodOn(PersonResource.class).getPersonList(0, 25)).withRel("list").withType("GET"))
                        .add(linkTo(methodOn(PersonResource.class).getPersonById(personId)).withSelfRel().withType("GET")));
    }

    @GetMapping
    public ResponseEntity<List<Person>> getPersonList(@RequestParam(value = "page", defaultValue = "0") int page,
                                                      @RequestParam(value = "limit", defaultValue = "25") int value) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personService.listPerson(page, value));
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
