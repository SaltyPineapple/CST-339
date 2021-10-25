package com.gcu.business;

import org.springframework.beans.factory.annotation.Autowired;

public class LoginBusinessService implements LoginInterface {

    @Autowired
    private LoginSecurityService service;

    @Override
    public boolean login(String username, String password) {
        return service.authenticate(username, password);
    }
    
}
