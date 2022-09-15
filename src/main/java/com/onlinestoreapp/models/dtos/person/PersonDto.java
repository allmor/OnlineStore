package com.onlinestoreapp.models.dtos.person;

import com.onlinestoreapp.models.Credentials;
import com.onlinestoreapp.models.Role;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class PersonDto {

    private Integer id;
    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 2, max = 33)
    private String name;
    @NotEmpty(message = "Surname should not be empty!")
    @Size(min = 2, max = 33)
    private String surname;

    private List<Role> roles;

    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
