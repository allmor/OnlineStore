package com.onlinestoreapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "person_email")
    @Email
    private String personEmail;

    @Column(name = "password")
    private String password;

    public Person() {
    }

    public Person(String personEmail, String password) {
        this.personEmail = personEmail;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", personEmail='" + personEmail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
