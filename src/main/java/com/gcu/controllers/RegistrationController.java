package com.gcu.controllers;

import javax.validation.Valid;

import com.gcu.business.RegistrationInterface;
import com.gcu.models.RegisterModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private RegistrationInterface registerService;
    
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

        // dependency injection method
        if(registerService.register(registerModel.getFirstName(), registerModel.getLastName(), registerModel.getUsername(), registerModel.getPassword(), registerModel.getPasswordRepeat())){
            System.out.printf("Successfully Registerred, Username of: %s", registerModel.getUsername());
            return "home";    
        }
        return "register";
    }
}
