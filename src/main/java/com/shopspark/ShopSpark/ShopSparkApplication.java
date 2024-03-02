package com.shopspark.ShopSpark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class ShopSparkApplication {
	public static void main(String[] args) {
		SpringApplication.run(ShopSparkApplication.class, args);
	}
}