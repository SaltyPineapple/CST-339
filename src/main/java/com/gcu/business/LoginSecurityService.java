package com.gcu.business;

import org.springframework.stereotype.Service;

@Service
public class LoginSecurityService {
    public boolean authenticate(String username, String password){
        // Hardcoded validations since databases will be implemented next week
        if(username.equals("jimmyJohn") && password.equals("password")){
            System.out.println("==================");
            System.out.println("Log in successfull!!");
            System.out.println("==================");
            return true;
        }
        System.out.println("==================");
        System.out.println("Log in Failed...");
        System.out.println("==================");
        return false;
    }
}
