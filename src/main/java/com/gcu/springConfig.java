package com.gcu;

import com.gcu.business.LoginBusinessService;
import com.gcu.business.LoginInterface;
import com.gcu.business.RegistrationBusinessService;
import com.gcu.business.RegistrationInterface;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class springConfig {
    
    @Bean(name="loginService")
    public LoginInterface getLogin(){
        return new LoginBusinessService();
    }

    @Bean(name="registerService")
    public RegistrationInterface getRegister(){
        return new RegistrationBusinessService();
    }
}
