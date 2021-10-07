package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class SlowReactiveServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlowReactiveServiceApplication.class, args);
	}

}
