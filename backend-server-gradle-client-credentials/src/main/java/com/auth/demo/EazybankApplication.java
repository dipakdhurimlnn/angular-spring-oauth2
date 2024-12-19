package com.auth.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.auth.demo")
@EnableJpaRepositories("com.auth.demo.repository")
@ComponentScan
public class EazybankApplication {

	public static void main(String[] args) {
		SpringApplication.run(EazybankApplication.class, args);
	}

}
