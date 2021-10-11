package com.gcu;

import com.gcu.business.LoginBusinessService;
import com.gcu.business.LoginInterface;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class springConfig {
    
    @Bean(name="loginService")
    public LoginInterface getLogin(){
        return new LoginBusinessService();
    }
}
