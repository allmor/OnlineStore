package com.onlinestoreapp.services;

import com.onlinestoreapp.models.Person;
import com.onlinestoreapp.models.dtos.person.PersonDto;
import com.onlinestoreapp.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PeopleRepository peopleRepository;
    private final RolesService rolesService;

    @Autowired
    public PersonService(PeopleRepository peopleRepository, RolesService rolesService) {
        this.peopleRepository = peopleRepository;
        this.rolesService = rolesService;
    }

    // method for creating personDTO object through person object
    // and putting it in edit controller method
    public PersonDto getPersonDtoById(Integer id) {
        Person person = peopleRepository.getPersonById(id);

        PersonDto personDto = new PersonDto();
        personDto.setId(id);
        personDto.setName(person.getName());
        personDto.setSurname(person.getSurname());
        personDto.setRoles(person.getRoles());
        personDto.setEmail(person.getCredentials().getPersonEmail());
        return personDto;
    }

    // save new person with default role - USER
    public void savePerson(Person person) {
        person.setRoles(List.of(rolesService.getDefaultRole()));
        peopleRepository.save(person);
    }

    public void editPerson(PersonDto personDto) {
        Person personForEdit = peopleRepository.getPersonById(personDto.getId());

        personForEdit.setName(personDto.getName());
        personForEdit.setSurname(personDto.getSurname());
        personForEdit.setRoles(personDto.getRoles());
        personForEdit.getCredentials().setPersonEmail(personDto.getEmail());

        peopleRepository.save(personForEdit);
    }

    public Optional<Person> findPerson(Person person) {
        return peopleRepository.findById(person.getId());
    }

    public List<Person> getAll() {
        return peopleRepository.findAll();
    }

    public void deletePerson(Integer id) {
        peopleRepository.deleteById(id);
    }
}
