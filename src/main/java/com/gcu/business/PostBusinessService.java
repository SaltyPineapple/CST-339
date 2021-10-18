package com.gcu.business;

import org.springframework.beans.factory.annotation.Autowired;

public class PostBusinessService implements PostInterface {

    @Autowired
    private PostSecurityService service;

    @Override
    public boolean createBlogPost(String title, String post) {
        return service.createPost(title, post);
    }
    
}
