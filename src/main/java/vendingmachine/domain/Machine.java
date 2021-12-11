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
		return checkItemsPossiblePurchase() && isInputCoinPossiblePurchase();
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

	private boolean checkItemsPossiblePurchase() {
		for (Item item : items) {
			if (!item.isSoldOut() && isPossiblePurchaseItem(item)) {
				return true;
			}
		}
		return false;
	}

	private boolean isInputCoinPossiblePurchase() {
		return inputCoins >= minItemPrice;
	}

	private void setMinItemPrice(List<Item> items) {
		minItemPrice = Collections.min(items, Comparator.comparing(Item::getPrice)).getPrice();
	}

	public Map<Integer, Integer> getReturnInputCoin() {
		return getReturnCoinMap(inputCoins);
	}

	private Map<Integer, Integer> getReturnCoinMap(Integer restCoin) {
		Map<Integer, Integer> coinMap = Coin.getSortedCoinMap(coins);
		Map<Integer, Integer> returnCoinMap = new LinkedHashMap<>();
		for (Integer coin : coinMap.keySet()) {
			restCoin = setReturnCoinMapByCoinAndReturnRestCoin(restCoin, coinMap, returnCoinMap, coin);
		}
		return returnCoinMap;
	}

	private Integer setReturnCoinMapByCoinAndReturnRestCoin(Integer restCoin, Map<Integer, Integer> coinMap,
		Map<Integer, Integer> returnCoinMap, Integer coin) {
		while (isPossibleReturnCoin(restCoin, coin, coinMap.get(coin))) {
			restCoin -= coin;
			coinMap.replace(coin, coinMap.get(coin) - 1);
			addValueInReturnMap(returnCoinMap, coin);
		}
		return restCoin;
	}

	private void addValueInReturnMap(Map<Integer, Integer> map, Integer key) {
		if (!map.containsKey(key)) {
			map.put(key, 1);
			return;
		}
		map.replace(key, map.get(key) + 1);
	}

	private Boolean isPossibleReturnCoin(final int restCoin, final int coin, final int count) {
		return restCoin >= coin && count > 0;
	}
}
