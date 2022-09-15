package com.onlinestoreapp.services;

import com.onlinestoreapp.models.Credentials;
import com.onlinestoreapp.models.Person;
import com.onlinestoreapp.repositories.CredentialRepository;
import com.onlinestoreapp.repositories.PeopleRepository;
import com.onlinestoreapp.security.PersonDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;
    private final CredentialRepository credentialRepository;

    public PersonDetailsService(PeopleRepository peopleRepository, CredentialRepository credentialRepository) {
        this.peopleRepository = peopleRepository;
        this.credentialRepository = credentialRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Credentials> credentials = credentialRepository.findByPersonEmail(username);

        Optional<Person> person = Optional.ofNullable(credentials.get().getPerson());

        if (person.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        } else {
            return new PersonDetails(person.get());
        }
    }
}
