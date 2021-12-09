package vendingmachine.domain;

import java.util.ArrayList;
import java.util.List;

public class Machine {

	private final List<Coin> coins = new ArrayList<>();
	private final List<Item> items = new ArrayList<>();
	private Integer inputCoins = 0;

	public void addCoins(List<Coin> coins) {
		this.coins.addAll(coins);
	}

	public void addItems(List<Item> items) {
		this.items.addAll(items);
	}

	public void addInputCoins(int amount) {
		inputCoins += amount;
	}

	public Integer getTotalCoinAmount() {
		int result = 0;
		for (Coin coin : coins) {
			result += coin.getAmount();
		}
		return result;
	}

	public void printCoins() {
		int coin_500 = 0;
		int coin_100 = 0;
		int coin_50 = 0;
		int coin_10 = 0;

		for (Coin coin : coins) {
			if (coin == Coin.COIN_500) {
				coin_500 += 1;
			}
			if (coin == Coin.COIN_100) {
				coin_100 += 1;
			}
			if (coin == Coin.COIN_50) {
				coin_50 += 1;
			}
			if (coin == Coin.COIN_10) {
				coin_10 += 1;
			}
		}
		System.out.println("500 : " + coin_500 + ", 100 : " + coin_100 + ", 50 : " + coin_50 + ", 10 : " + coin_10);
	}
}
