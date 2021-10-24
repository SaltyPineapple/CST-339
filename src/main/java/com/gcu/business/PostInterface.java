package com.gcu.business;

import java.util.List;

import com.gcu.models.BlogPostModel;

public interface PostInterface {
    public boolean createBlogPost(String title, String post);
    public List<BlogPostModel> findAll();
}

