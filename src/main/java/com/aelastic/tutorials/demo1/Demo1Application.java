package com.aelastic.tutorials.demo1;

import com.aelastic.tutorials.demo1.service.TweetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Demo1Application {

    public static void main(String[] args) {

        SpringApplication.run(Demo1Application.class, args);


    }


    @Bean
    public CommandLineRunner commandLineRunner(TweetsService tweetsService) {
        return (args) -> tweetsService.getTweets();
    }
}