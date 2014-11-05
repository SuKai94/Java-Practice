package com.susu.designpatterns.celuemoshi;

public class PriceContext {

	private Strategy strategy;

	public PriceContext(String strategyType) {
		switch (strategyType) {
		case "Primary":
			strategy = new PrimaryStrategy();
			break;
		case "Media":
			strategy = new MediaStrategy();
			break;
		case "Advance":
			strategy = new AdvanceStrategy();
			break;
		}
	}

	public double getFinalPrice(double booksPrice) {
		return this.strategy.calPrice(booksPrice);
	}

}
