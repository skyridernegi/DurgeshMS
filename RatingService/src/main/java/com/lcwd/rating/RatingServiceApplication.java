package com.lcwd.rating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//(scanBasePackages = { "com.lcwd.rating.service", "com.lcwd.rating.service.services"})
//@ComponentScan("com.lcwd.rating.service.services")
//@ComponentScan({"com.lcwd.rating.service","com.lcwd.rating.service.services"})
public class RatingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingServiceApplication.class, args);
	}

}
