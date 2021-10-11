package com.gcu.activity22;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.gcu"})
public class Activity22Application {

	public static void main(String[] args) {
		SpringApplication.run(Activity22Application.class, args);
	}

}
