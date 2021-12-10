package vendingmachine.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

	public Map<Integer, Integer> getCoins() {
		return Coin.getSortedCoinMap(coins);
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
		return checkItemsSoldOut();
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

	public boolean isPossiblePurchaseItem(Item item) {
		return this.inputCoins >= item.getPrice();
	}

	public void reduceInputCoin(Integer price) {
		this.inputCoins -= price;
	}

	private boolean checkItemsSoldOut() {
		for (Item item : items) {
			if (!item.isSoldOut()) {
				return true;
			}
		}
		return false;
	}

	private boolean checkInputCoins() {
		return inputCoins < minItemPrice;
	}

	private void setMinItemPrice(List<Item> items) {
		minItemPrice = Collections.min(items, Comparator.comparing(Item::getPrice)).getPrice();
	}

	public Map<Integer, Integer> getRestInputCoin() {
		return getReturnCoinMap(inputCoins);
	}

	private Map<Integer, Integer> getReturnCoinMap(Integer restCoin) {
		Map<Integer, Integer> coinMap = Coin.getSortedCoinMap(coins);
		Map<Integer, Integer> returnCoinMap = new LinkedHashMap<>();
		for (Integer coin : coinMap.keySet()) {
			setReturnCoinMapByCoin(restCoin, coinMap, returnCoinMap, coin);
		}
		return returnCoinMap;
	}

	private void setReturnCoinMapByCoin(Integer restCoin, Map<Integer, Integer> coinMap,
		Map<Integer, Integer> returnCoinMap, Integer coin) {
		while (restCoin >= coin && coinMap.get(coin) > 0) {
			coinMap.replace(coin, coinMap.get(coin) - 1);
			restCoin -= coin;
			if (!returnCoinMap.containsKey(coin)) {
				returnCoinMap.put(coin, 1);
				continue;
			}
			returnCoinMap.replace(coin, returnCoinMap.get(coin) + 1);
		}
	}
}
