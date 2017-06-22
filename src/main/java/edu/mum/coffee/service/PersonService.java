package edu.mum.coffee.service;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Role;
import edu.mum.coffee.exception.EmailTakenException;
import edu.mum.coffee.exception.PasswordConfirmNotMatch;
import edu.mum.coffee.exception.UsernameTakenException;
import edu.mum.coffee.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public Person findByUsername(String username) {
        return personRepository.findByUsername(username);
    }

    public List<Person> findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    public Person findById(Long id) {
        return personRepository.findOne(id);
    }

    public void removePerson(Person person) {
        personRepository.delete(person);
    }

    public void removePerson(Long id) {
        personRepository.delete(id);
    }

    public Person registerNewPerson(Person person) throws EmailTakenException, UsernameTakenException, PasswordConfirmNotMatch {
        String username = person.getUsername();
        String email = person.getEmail();
        String password = person.getPassword();
        String passwordConfirm = person.getPasswordConfirm();

        if (findByUsername(username) != null) {
            throw new UsernameTakenException("Username is already taken: " + username);
        }

        if (!findByEmail(email).isEmpty()) {
            throw new EmailTakenException("Email is already exists: " + email);
        }

        if (!password.equals(passwordConfirm)) {
            throw new PasswordConfirmNotMatch();
        }

        person.addRole(new Role("ROLE_USER"));
        person.setEncryptedPassword(passwordEncoder.encode(person.getPassword()));

        return personRepository.save(person);
    }
}
