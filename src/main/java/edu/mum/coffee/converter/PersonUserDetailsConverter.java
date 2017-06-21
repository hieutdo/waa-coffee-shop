package edu.mum.coffee.converter;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.security.SimpleUserDetails;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class PersonUserDetailsConverter implements Converter<Person, UserDetails> {
    @Override
    public UserDetails convert(Person person) {
        SimpleUserDetails userDetails = new SimpleUserDetails();
        if (person != null) {
            userDetails.setUsername(person.getUsername());
            userDetails.setPassword(person.getEncryptedPassword());
            userDetails.setEnabled(person.getEnable());
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            person.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getRole()));
            });
            userDetails.setAuthorities(authorities);
        }
        return userDetails;
    }
}
