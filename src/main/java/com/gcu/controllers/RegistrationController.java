package com.gcu.controllers;

import javax.validation.Valid;

import com.gcu.models.RegisterModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    
    @GetMapping("/")
    public String display(Model model){
        model.addAttribute("title", "Registration Form");
        model.addAttribute("registerModel", new RegisterModel());
        return "register";
    }

    @PostMapping("/doRegister")
    public String doRegister(@Valid RegisterModel registerModel, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("title", "Registration Form");
            return "register";
        }
        System.out.printf("Successfully Registerred, Username of: %s", registerModel.getUsername());
        return "home";
    }
}
