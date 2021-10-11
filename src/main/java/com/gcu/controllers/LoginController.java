package com.gcu.controllers;

import javax.validation.Valid;

import com.gcu.business.LoginInterface;
import com.gcu.models.LoginModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginInterface loginService;
    
    @GetMapping("/")
    public String display(Model model){
        model.addAttribute("title", "Login Form");
        model.addAttribute("loginModel", new LoginModel());
        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(@Valid LoginModel loginModel, BindingResult bindingResult, Model model){

        // check for validaton errors
        if(bindingResult.hasErrors()){
            model.addAttribute("title", "Login Form");
            return "login";
        }

        if(loginService.login(loginModel.getUsername(), loginModel.getPassword())){
            System.out.println(String.format("Form with Username of %s and Password of %s", loginModel.getUsername(), loginModel.getPassword()));
        
            return "home";
        }
        return "login";
        
    }

}
