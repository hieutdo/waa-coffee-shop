package edu.mum.coffee.security;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SimpleUserDetailsService implements UserDetailsService {
    private PersonService personService;
    private Converter<Person, UserDetails> personUserDetailsConverter;

    @Autowired
    public SimpleUserDetailsService(PersonService personService, Converter<Person, UserDetails> personUserDetailsConverter) {
        this.personService = personService;
        this.personUserDetailsConverter = personUserDetailsConverter;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personUserDetailsConverter.convert(personService.findByUsername(username));
    }
}
