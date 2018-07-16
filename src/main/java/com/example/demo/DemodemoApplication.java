package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DemodemoApplication extends SpringBootServletInitializer {
    // SpringBootServletInitializer is needed if you want to deploy into a tomcat
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DemodemoApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DemodemoApplication.class, args);
    }
}
