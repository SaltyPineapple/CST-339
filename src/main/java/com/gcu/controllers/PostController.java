package com.gcu.controllers;

import javax.validation.Valid;

import com.gcu.business.PostInterface;
import com.gcu.models.BlogPostModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostInterface postService;

    @GetMapping("/")
    public String display(Model model){
        model.addAttribute("title", "Post Form");
        model.addAttribute("postModel", new BlogPostModel());
        return "createPost";
    }
    
    @PostMapping("/doPost")
    public String doLogin(@Valid BlogPostModel post, BindingResult bindingResult, Model model){

        // check for validaton errors
        if(bindingResult.hasErrors()){
            model.addAttribute("title", "Post Form");
            return "createPost";
        }

        if(postService.createBlogPost(post.getTitle(), post.getPost())){
            System.out.println(String.format("Post created with title of %s", post.getTitle()));
        
            return "home";
        }
        return "createPost";
        
    }
}
