package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.StockQuote;
import com.example.service.StockService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

@RestController
public class StockController {
	
	
	@Autowired
	StockService service;
	
	@GetMapping(value="/quotes", produces="application/stream+json")
	public ParallelFlux<Object> getAllQuotes(){
		return service.getAllQuotes();
	}
	
	
	@GetMapping("/pieceofcake")
	public String someEasyToComputeInfo() {
		return "zzzzzzzzzzzzz";
	}

	@GetMapping(value="/quotes-np", produces="application/stream+json")
	public Flux<Object> getAllQuotesNP(){
		return service.getAllQuotesNP();
	}
	
	@GetMapping("/quotes/{num}")
	public Mono<StockQuote> getQuote(@PathVariable("num") int num){
		return Mono.fromCallable(() -> service.getQuote(num)).subscribeOn(Schedulers.boundedElastic());
		
	}
}
