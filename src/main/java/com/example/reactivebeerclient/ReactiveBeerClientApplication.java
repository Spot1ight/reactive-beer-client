package com.example.reactivebeerclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.r2dbc.ConnectionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

@SpringBootApplication
public class ReactiveBeerClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveBeerClientApplication.class, args);
	}

	@Value("classpath:/schema.sql")
	Resource resource;



}
