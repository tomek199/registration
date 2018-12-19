package com.exercise.registration.dto;

import com.exercise.registration.validator.UniqueUsername;

import javax.validation.constraints.Pattern;

/**
 * Class using to transfer user object
 */
public class User {

    @Pattern(regexp = "^[a-zA-Z0-9]{5,}$")
    @UniqueUsername
    private String username;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
