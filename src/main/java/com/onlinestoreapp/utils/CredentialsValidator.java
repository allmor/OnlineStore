package com.onlinestoreapp.utils;

import com.onlinestoreapp.models.Credentials;
import com.onlinestoreapp.repositories.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CredentialsValidator implements Validator {
    private final CredentialRepository credentialRepository;

    @Autowired
    public CredentialsValidator(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Credentials.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Credentials credentials = (Credentials) target;

        if (credentialRepository.findByPersonEmail(credentials.getPersonEmail()).isPresent()) {
            errors.rejectValue("email", "", "This email is already taken!");
        }
    }
}
