package com.example.mathapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MathApiSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MathApiSpringbootApplication.class, args);
	}

}
