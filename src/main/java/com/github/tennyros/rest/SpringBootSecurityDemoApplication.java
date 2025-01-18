package com.github.tennyros.rest;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.out.println("DB_MS_USERNAME: " + dotenv.get("DB_MS_USERNAME"));
		System.setProperty("DB_MS_USERNAME", dotenv.get("DB_MS_USERNAME"));
		System.setProperty("DB_MS_PASSWORD", dotenv.get("DB_MS_PASSWORD"));
		System.setProperty("DB_MS_PASSWORD", dotenv.get("DB_MS_PASSWORD"));
		System.setProperty("DB_MS_SCHEMA_NAME", dotenv.get("DB_MS_SCHEMA_NAME"));
		System.setProperty("MAIN_ADM_FIRSTNAME", dotenv.get("MAIN_ADM_FIRSTNAME"));
		System.setProperty("MAIN_ADM_LASTNAME", dotenv.get("MAIN_ADM_LASTNAME"));
		System.setProperty("MAIN_ADM_EMAIL", dotenv.get("MAIN_ADM_EMAIL"));
		System.setProperty("MAIN_ADM_PASSWORD", dotenv.get("DB_MS_SCHEMA_NAME"));
		System.setProperty("MAIN_ADM_AGE", dotenv.get("MAIN_ADM_AGE"));
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
	}
}
