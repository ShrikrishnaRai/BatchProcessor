package com.shree.batch.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shree.batch.model.Person;
import com.shree.batch.services.person.PersonService;
import com.shree.batch.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/firstName")
    public ResponseEntity<Person> getPersonByFirstName(@RequestParam String firstName) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personService.findPersonByFirstName(firstName)
                        .add(linkTo(methodOn(PersonResource.class).getPersonList(0, 25)).withRel("list").withType("GET"))
                        .add(linkTo(methodOn(PersonResource.class).getPersonByFirstName(firstName)).withSelfRel().withType("GET")));
    }

    @GetMapping("/email")
    public ResponseEntity<Person> getPersonByEmail(@RequestParam String email) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personService.findPersonByEmail(email)
                        .add(linkTo(methodOn(PersonResource.class).getPersonList(0, 25)).withRel("list").withType("GET"))
                        .add(linkTo(methodOn(PersonResource.class).getPersonByEmail(email)).withSelfRel().withType("GET")));
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

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadPersonData(@RequestParam(value = "page", defaultValue = "0") int page,
                                                    @RequestParam(value = "limit", defaultValue = "25") int limit) throws Exception {
        byte[] personByte = personService.downloadPersonDetails(page, limit);
        String fileName = "person.json";
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentLength(personByte.length);
        respHeaders.setContentType(new MediaType("text", "json"));
        respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        return new ResponseEntity<>(personByte, respHeaders, HttpStatus.OK);
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<String> uploadPersonData(
            @RequestParam("data") final MultipartFile multipartFile) throws Exception {
        return ResponseEntity.status(HttpStatus.OK)
                .body("Hello World");
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> updateVehicle(@PathVariable(value = "id") long id,
                                                         @RequestBody Person person){
        return new ResponseEntity<>(personService.updateVehicle(id, person), HttpStatus.OK);
    }
}
