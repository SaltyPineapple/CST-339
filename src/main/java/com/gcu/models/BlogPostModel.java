package com.gcu.models;

import javax.validation.constraints.NotNull;

public class BlogPostModel {
    
    @NotNull(message="Title is required")
    private String title;

    @NotNull(message="Post cannot be blank")
    private String post;
    
    private String timestamp;

    public BlogPostModel(String title, String post, String timestamp){
        this.title = title;
        this.post = post;
        this.timestamp = timestamp;
    }

    public BlogPostModel(){
        
    }

    

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String postTitle) {
        this.title = postTitle;
    }

    public String getPost() {
        return this.post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }



}
