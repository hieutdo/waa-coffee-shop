package edu.mum.coffee.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Set<Person> persons;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    public void addPerson(Person person) {
        if (!this.persons.contains(person)) {
            this.persons.add(person);
        }
        if (!person.getRoles().contains(this)) {
            person.getRoles().add(this);
        }
    }

    public void removePerson(Person person) {
        this.persons.remove(person);
        person.getRoles().remove(this);
    }
}
