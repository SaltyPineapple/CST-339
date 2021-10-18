package com.gcu.business;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PostSecurityService {

    @Autowired
    @SuppressWarnings("unused")
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public PostSecurityService(DataSource dataSource){
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean createPost(String title, String post){
        String sql = "INSERT INTO blogpost (title, post, timestamp) values (?,?,?)";
        Date date =  new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String newDate = formatter.format(date);
        try{
            int rows = jdbcTemplate.update(sql, title, post, newDate);
            return rows == 1 ?true:false;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("\n======================");
        System.out.println("Post Creation Failure...");
        System.out.println("======================");
        return false;

    }
    
}
