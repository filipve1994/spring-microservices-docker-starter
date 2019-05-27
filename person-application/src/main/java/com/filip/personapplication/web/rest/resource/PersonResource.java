package com.filip.personapplication.web.rest.resource;

import java.util.List;
import java.util.Optional;

import com.filip.personapplication.db.entities.Person;
import com.filip.personapplication.db.repository.PersonRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/person")
@Api(tags = "Person Queries", value = "PersonQueries")
public class PersonResource {

    private static final Logger logger = LoggerFactory.getLogger(PersonResource.class);


    //Ideally you shall be using Service classes
    @Autowired
    PersonRepository personRepo;

    @ApiOperation(value = "Get all persons")
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity<List<Person>> getAllPersons() {
    public ResponseEntity getAllPersons() {

        Person p1 = new Person();
        p1.setEmail("email@email.com");
        p1.setFirstName("firstname");
        p1.setLastName("lastname");
        personRepo.save(p1);

        logger.info("personresource - getAllPersons - ");
        List<Person> all = personRepo.findAll();
        logger.info("size of all persons: " + all.size());

        return ResponseEntity.ok(personRepo.findAll());
    }

    @ApiOperation(value = "Get a person by ID")
    @GetMapping(value = "/{personId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity<Person> getPersonById(@PathVariable("personId") int personID) {
    public ResponseEntity getPersonById(@PathVariable("personId") int personID) {
        Optional<Person> personInDB = personRepo.findById(personID);
        if (personInDB.isPresent()) {
            return ResponseEntity.ok(personInDB.get());
        } else {
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity<Person> storePerson(@RequestBody Person person) {
    public ResponseEntity storePerson(@RequestBody Person person) {
        Person personInDB = personRepo.save(person);
        return new ResponseEntity<Person>(personInDB, HttpStatus.CREATED);
    }

    @PutMapping("/{personId}")
    public ResponseEntity<Person> updatePersonDetails(@PathVariable("personId") int personID, @RequestBody(required = true) Person personDataToBeUpdated) {

        if (personID != personDataToBeUpdated.getId()) {    //Just to make sure we have same person_id in path param and body.
            return new ResponseEntity<Person>(HttpStatus.BAD_REQUEST);
        }

        Optional<Person> personInDB = personRepo.findById(personID);
        if (personInDB.isPresent()) {
            Person person = personRepo.saveAndFlush(personDataToBeUpdated);
            return ResponseEntity.ok(person);
        } else {
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }
    }
}
