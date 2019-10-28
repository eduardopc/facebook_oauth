package com.igti.facebookoauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class FacebookOauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacebookOauthApplication.class, args);
	}

}
