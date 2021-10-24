package com.gcu.business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import com.gcu.models.BlogPostModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
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

    public List<BlogPostModel> findAll(){
        String sql = "Select * FROM blogpost";
        List<BlogPostModel> posts = new ArrayList<>();
        try{
            SqlRowSet srs = jdbcTemplate.queryForRowSet(sql);
            while(srs.next()){
                posts.add(new BlogPostModel(srs.getString("title"),
                                            srs.getString("post"),
                                            srs.getString("timestamp")));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    

        return posts;
        
    }
    
}
