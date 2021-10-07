package com.example.domain;

public class StockQuote {
	
	String symbol;
	float price;
	
	public StockQuote() {
		
	}

	public StockQuote(String symbol, float price) {
		this.symbol = symbol;
		this.price = price;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "StockQuote [symbol=" + symbol + ", price=" + price + "]";
	}
	
	

}
