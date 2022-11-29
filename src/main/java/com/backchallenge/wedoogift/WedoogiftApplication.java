package com.backchallenge.wedoogift;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Wedoogift API",
				description = "Wedoogift API is an API to manage gift and meal deposit",
				version = "1.0"))
public class WedoogiftApplication {

	public static void main(String[] args) {
		SpringApplication.run(WedoogiftApplication.class, args);
	}

}
