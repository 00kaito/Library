package com.example.jpa.Model.forms;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegisterForm {
    @Size (min = 5, max=20)
    private String login;
    @Size(min = 5, max = 20)
    private String password;
    @Pattern(regexp = "[A-Za-z]+ [A-Za-z]+") //imie nazwisko
    private String name;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
