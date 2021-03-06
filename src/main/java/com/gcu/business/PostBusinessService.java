package com.gcu.business;

import java.util.List;

import com.gcu.models.BlogPostModel;

import org.springframework.beans.factory.annotation.Autowired;

public class PostBusinessService implements PostInterface {

    @Autowired
    private PostSecurityService service;

    @Override
    public boolean createBlogPost(String title, String post) {
        return service.createPost(title, post);
    }


    @Override
    public List<BlogPostModel> findAll() {
        return service.findAll();
    }

    @Override
    public BlogPostModel findByID(int id){
        return service.findByID(id);
    }

    @Override
    public boolean deletePost(int id){
        return service.deletePost(id);
    }
    
}
