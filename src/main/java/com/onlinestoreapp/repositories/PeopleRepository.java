package com.onlinestoreapp.repositories;

import com.onlinestoreapp.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    List<Person> findAll();

    Person getPersonById(Integer id);

    void deleteById(Integer id);
}
