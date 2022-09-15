package com.onlinestoreapp.services;

import com.onlinestoreapp.models.Credentials;
import com.onlinestoreapp.models.Person;
import com.onlinestoreapp.repositories.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialsService {
    private final PersonService personService;
    private final CredentialRepository credentialRepository;

    @Autowired
    public CredentialsService(PersonService personService, CredentialRepository credentialRepository) {
        this.personService = personService;
        this.credentialRepository = credentialRepository;
    }

    public void savePersonAndCredentials(Person person, Credentials credentials) {
        personService.savePerson(person);

        credentialRepository.save(credentials);
    }
}
