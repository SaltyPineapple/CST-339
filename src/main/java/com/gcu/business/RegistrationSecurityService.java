package com.gcu.business;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class RegistrationSecurityService {

    @Autowired
    @SuppressWarnings("unused")
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public RegistrationSecurityService(DataSource dataSource){
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    
    // Method will insert a user into the database from the registration page.
    public boolean register(String firstName, String lastName, String username, String password, String passwordRepeat){

        String sql = "INSERT INTO USERS (firstname, lastname, username, password) values (?,?,?,?)";


        if(password.equals(passwordRepeat)){
            try{
                int rows = jdbcTemplate.update(sql, firstName, lastName, username, password);
                return rows == 1 ? true : false;
            }
            catch (Exception e){
                e.printStackTrace();
            }
            
        }
        System.out.println("\n======================");
        System.out.println("Registration Failure...");
        System.out.println("======================");
        return false;
    }
}
