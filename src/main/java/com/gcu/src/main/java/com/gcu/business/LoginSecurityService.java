package com.gcu.business;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service
public class LoginSecurityService {

    @Autowired
    @SuppressWarnings("unused")
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public LoginSecurityService(DataSource dataSource){
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // This method houses SQL Queries to log the user in
    // checks if the username and password combo exists in the database
    public boolean authenticate(String username, String password){

        String sql = String.format("Select * From Users where USERNAME='%s' and PASSWORD='%s'",username,password);
        try{
            SqlRowSet srs = jdbcTemplate.queryForRowSet(sql);
            if(srs.first()){
                return true;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;

    }
}
