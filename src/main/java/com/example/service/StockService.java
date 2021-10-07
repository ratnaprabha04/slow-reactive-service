package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.domain.StockQuote;

import reactor.core.publisher.Flux;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

@Service
public class StockService {

	@Value("${delay.per.stock}")
	int delayPerStock;
	
	@Value("${number.of.stocks}")
	int numberOfStocks;
	
	List<StockQuote> stockList = new ArrayList<>();
	
	Random r = new Random();
	
	
	public Flux<Object> getAllQuotesNP(){
		
		 Flux<Object> flux = Flux.range(0, numberOfStocks)
				.publishOn(Schedulers.parallel())
                .map(i -> {
//                   System.out.println("Mapping for stock" + i + " is done by thread " + Thread.currentThread().getName());
                   return getQuote(i);
               });
		 return flux;
		
	}
	
	public ParallelFlux<Object> getAllQuotes(){
		
		 ParallelFlux<Object> flux = Flux.range(0, numberOfStocks)
				.parallel(3)
                .runOn(Schedulers.parallel())
                .map(i -> {
//                    System.out.println("Mapping for stock" + i + " is done by thread " + Thread.currentThread().getName());
                    return getQuote(i);
                });
		 return flux;
		
	}
	
	//--------------------------------------- util methods follow
	
	@PostConstruct
	public void init() {
		
		for(int i = 1;i <= numberOfStocks; i++) {
			int randomWhole = r.nextInt(1000);
			float randomFraction = ((float) r.nextInt(100))/10; 
			StockQuote s = new StockQuote("Stock"+i, (randomWhole+randomFraction));
			stockList.add(s);
		}
	}
	
	public StockQuote getQuote(int i) {
		StockQuote s = stockList.get(i);
		randomPriceChange(s);
		sleep(delayPerStock);
		return s;
	}


	private void randomPriceChange(StockQuote s) {
		float deltaChange = ((r.nextFloat() * 5 )/100)*s.getPrice();
		if(r.nextBoolean()) {
			s.setPrice(s.getPrice()+deltaChange);
		}else {
			s.setPrice(s.getPrice() - deltaChange);
		}
		
	}


	private void sleep(int delayPerStock2) {
		try {
			Thread.sleep(delayPerStock2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
