package com.onlinestoreapp.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "person_credentials")
public class Credentials implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person person;

    @Column(name = "person_email")
    @Email
    @NotEmpty
    private String personEmail;

    @Column(name = "person_password")
    @NotEmpty
    @Size(min = 8, max = 32)
    private String personPassword;

    public Credentials() {
    }

    public Credentials(Person person, String personEmail, String personPassword) {
        this.person = person;
        this.personEmail = personEmail;
        this.personPassword = personPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonPassword() {
        return personPassword;
    }

    public void setPersonPassword(String personPassword) {
        this.personPassword = personPassword;
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "id=" + id +
                ", person=" + person +
                ", personEmail='" + personEmail + '\'' +
                ", personPassword='" + personPassword + '\'' +
                '}';
    }
}
