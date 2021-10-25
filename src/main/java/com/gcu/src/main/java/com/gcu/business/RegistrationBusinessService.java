package com.gcu.business;

import org.springframework.beans.factory.annotation.Autowired;

public class RegistrationBusinessService implements RegistrationInterface{

    @Autowired
    private RegistrationSecurityService service;

    @Override
    public boolean register(String firstName, String lastName, String username, String password, String passwordRepeat) {
        return service.register(firstName, lastName, username, password, passwordRepeat);
    }
    
}
