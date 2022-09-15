package com.onlinestoreapp.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "person_name")
    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 2, max = 33)
    private String name;

    @Column(name = "person_surname")
    @NotEmpty(message = "Surname should not be empty!")
    @Size(min = 2, max = 33)
    private String surname;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "persons_roles",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private List<Role> roles;

    @OneToOne(mappedBy = "person",
            fetch = FetchType.EAGER)
    private Credentials credentials;

    @OneToOne(mappedBy = "person")
    private Cart cart;

    public Person() {
    }

    public Person(String name, String surname, List<Role> roles) {
        this.name = name;
        this.surname = surname;
        this.roles = roles;
    }

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
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public Cart getCart() {
        if (this.cart == null) {
            this.cart = new Cart();
        }
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", roles=" + roles +
                '}';
    }
}
