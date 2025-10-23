package com.finangen.Finangen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan(basePackages = "com.finangen")
@EntityScan(basePackages = {"com.finangen.domains", "com.finangen.domains.enums"})
@EnableJpaRepositories(basePackages = "com.finangen.repositories")
@SpringBootApplication
public class FinangenApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinangenApplication.class, args);
	}

}
