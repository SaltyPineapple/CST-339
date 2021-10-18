package com.gcu.models;

public class BlogPostModel {
    
    private String title;
    private String post;
    private String timestamp;

    public BlogPostModel(String title, String post, String timestamp){
        this.title = title;
        this.post = post;
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
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
