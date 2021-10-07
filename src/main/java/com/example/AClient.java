package com.example;

import org.springframework.web.reactive.function.client.WebClient;

import com.example.domain.StockQuote;

public class AClient {

	public static void main(String[] args) throws Exception {
		WebClient wc = WebClient.create("http://localhost:8080");
		
		wc.get()
			.uri("/quotes")
			.retrieve()
			.bodyToFlux(StockQuote.class)
			.subscribe(System.out::println);

		
		Thread.sleep(11000);
	}

}
