package com.gcu.business;

import org.springframework.stereotype.Service;

@Service
public class RegistrationSecurityService {
    public boolean register(String firstName, String lastName, String username, String password, String passwordRepeat){
        if(password.equals(passwordRepeat)){
            // Will implement JDBC Code Here
            System.out.println("\n======================");
            System.out.println("Successfully Registered!");
            System.out.println("======================");
            return true;
        }
        System.out.println("\n======================");
        System.out.println("Registration Failure...");
        System.out.println("======================");
        return false;
    }
}
