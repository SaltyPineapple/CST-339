package com.gcu.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterModel {
    @NotNull(message="First Name is a required field")
    @Size(min = 1, max = 32, message="First Name must be between 1 and 32 characters")
    private String firstName;

    @NotNull(message="Last Name is a required field")
    @Size(min = 1, max = 32, message="Last Name must be between 1 and 32 characters")
    private String lastName;

    @NotNull(message="Username is a required field")
    @Size(min = 1, max = 32, message="Username must be between 1 and 32 characters")
    private String username;

    @NotNull(message="Password is a required field")
    @Size(min = 1, max = 32, message="Password must be between 1 and 32 characters")
    private String password;

    @NotNull(message="Repeated Password is a required field")
    @Size(min = 1, max = 32, message="Passwords must match")
    private String passwordRepeat;


    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeat() {
        return this.passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

}
