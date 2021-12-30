package com.springBajo8.springBajo8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class SpringBajo8Application {
	public static void main(String[] args) {
		SpringApplication.run(SpringBajo8Application.class, args);
	}
}
