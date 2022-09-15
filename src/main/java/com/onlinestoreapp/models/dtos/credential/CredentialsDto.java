package com.onlinestoreapp.models.dtos.credential;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CredentialsDto {
    @Email
    @NotEmpty
    private String personEmail;
    @NotEmpty
    @Size(min = 8, max = 32)
    private String personPassword;
    @NotEmpty
    @Size(min = 8, max = 32)
    private String personPasswordRepeat;
    @NotEmpty
    private String personName;
    @NotEmpty
    private String personSurname;

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

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonSurname() {
        return personSurname;
    }

    public void setPersonSurname(String personSurname) {
        this.personSurname = personSurname;
    }

    public String getPersonPasswordRepeat() {
        return personPasswordRepeat;
    }

    public void setPersonPasswordRepeat(String personPasswordRepeat) {
        this.personPasswordRepeat = personPasswordRepeat;
    }
}
