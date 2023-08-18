package com.itea.finalproj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.itea.finalproj.repositories")
public class FinalprojApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalprojApplication.class, args);
	}

}
