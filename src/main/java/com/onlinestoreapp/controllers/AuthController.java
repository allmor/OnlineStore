package com.onlinestoreapp.controllers;

import com.onlinestoreapp.models.Credentials;
import com.onlinestoreapp.models.Person;
import com.onlinestoreapp.models.dtos.CredentialsDto;
import com.onlinestoreapp.services.CredentialsService;
import com.onlinestoreapp.utils.CredentialsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final CredentialsService credentialsService;
    private final CredentialsValidator credentialsValidator;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(CredentialsService credentialsService, CredentialsValidator credentialsValidator, PasswordEncoder passwordEncoder) {
        this.credentialsService = credentialsService;

        this.credentialsValidator = credentialsValidator;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String getSignInPage(Model model) {
        model.addAttribute(new Credentials());
        return "/auth/login";
    }

    @PostMapping("/process_login")
    public String loggingProcess(@ModelAttribute("person") @Valid Person person,
                                 BindingResult bindingResult) {
        return "redirect:/";
    }

    @GetMapping("/registration")
    public String getSignUpPage(Model model) {
        model.addAttribute("personCredentials",new CredentialsDto());
        return "/auth/registration";
    }

    @PostMapping("/process_registration")
    public String registrationProcess(@ModelAttribute("personCredentials") CredentialsDto personCredentials,
                                      BindingResult bindingResult) {

        Credentials credentials = new Credentials();

        credentials.setPersonEmail(personCredentials.getPersonEmail());
        credentials.setPersonPassword(passwordEncoder.encode(personCredentials.getPersonPassword()));


        credentialsValidator.validate(credentials, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/auth/registration";
        }
        if(!personCredentials.getPersonPassword().equals(personCredentials.getPersonPasswordRepeat())) {
           // bindingResult.addError(new FieldError("personPasswordRepeat", ""));
            return "/auth/registration";
        }

        Person person = new Person();

        person.setName(personCredentials.getPersonName());
        person.setSurname(personCredentials.getPersonSurname());
        credentials.setPerson(person);

        credentialsService.savePersonAndCredentials(person, credentials);

        return "redirect:/";
    }
}
