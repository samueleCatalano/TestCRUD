package com.testcrud.testcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TestCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestCrudApplication.class, args);
	}

}
