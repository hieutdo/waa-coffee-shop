package edu.mum.coffee.rest;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.exception.HttpClientErrorException;
import edu.mum.coffee.exception.HttpNotFoundErrorException;
import edu.mum.coffee.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonResource {
    private PersonService personService;

    @Autowired
    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody @Valid Person person) throws URISyntaxException {
        if (person.getId() != null) {
            throw new HttpClientErrorException("A person to be created cannot have id attribute");
        }
        Person result = personService.savePerson(person);
        return ResponseEntity.created(new URI("/api/persons/" + result.getId())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody @Valid Person person) throws URISyntaxException {
        if (personService.findById(id) == null) {
            throw new HttpNotFoundErrorException("Cannot update because the person with id " + id + " does not exist");
        }
        person.setId(id);
        return ResponseEntity.ok(personService.savePerson(person));
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        Person person = personService.findById(id);
        if (person == null) {
            throw new HttpNotFoundErrorException("Person with id " + id + " does not exist");
        }
        return ResponseEntity.ok(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable Long id) {
        if (personService.findById(id) == null) {
            throw new HttpNotFoundErrorException("Cannot delete because the person with id " + id + " does not exist");
        }
        personService.removePerson(id);
        return ResponseEntity.ok().build();
    }
}
