package vendingmachine.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Machine {

	private final List<Coin> coins = new ArrayList<>();
	private final List<Item> items = new ArrayList<>();
	private Integer inputCoins = 0;
	private Integer minItemPrice = 0;

	public void addCoins(List<Coin> coins) {
		this.coins.addAll(coins);
	}

	public void addItems(List<Item> items) {
		this.items.addAll(items);
		setMinItemPrice(items);
	}

	public void addInputCoins(int amount) {
		inputCoins += amount;
	}

	public Boolean isSoldOutAllItems() {
		for (Item item : items) {
			if (!item.isSoldOut()) {
				return false;
			}
		}
		return true;
	}

	public List<Coin> getCoins() {
		return coins;
	}

	public Integer getTotalCoinAmount() {
		int result = 0;
		for (Coin coin : coins) {
			result += coin.getAmount();
		}
		return result;
	}

	public Integer getInputCoins() {
		return inputCoins;
	}

	public Boolean isPossiblePurchase() {
		if (checkInputCoins())
			return false;
		return checkItemsQuantity();
	}

	private boolean checkItemsQuantity() {
		for (Item item : items) {
			if (item.getQuantity() > 0) {
				return true;
			}
		}
		return false;
	}

	private boolean checkInputCoins() {
		return inputCoins < minItemPrice;
	}

	public boolean isItemIn(String itemName) {
		for (Item item : items) {
			if (item.getName().equals(itemName)) {
				return true;
			}
		}
		return false;
	}

	public Item getItem(String itemName) {
		for (Item item : items) {
			if (item.getName().equals(itemName)) {
				return item;
			}
		}
		return null;
	}

	private void setMinItemPrice(List<Item> items) {
		minItemPrice = Collections.min(items, Comparator.comparing(Item::getPrice)).getPrice();
	}

	public boolean isPossiblePurchaseItem(Item item) {
		return this.inputCoins >= item.getPrice();
	}

	public void reduceInputCoin(Integer price) {
		this.inputCoins -= price;
	}
}
