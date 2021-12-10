package vendingmachine.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum Coin {
	COIN_500(500),
	COIN_100(100),
	COIN_50(50),
	COIN_10(10);

	private final int amount;

	Coin(final int amount) {
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public static Coin getThisAmountCoin(final int amount) {
		for (Coin coin : Coin.values()) {
			if (coin.getAmount() == amount) {
				return coin;
			}
		}
		return null;
	}

	public static Map<Integer, Integer> getSortedCoinMap(List<Coin> coinList) {
		Map<Integer, Integer> coinMap = getCoinMap();
		setCoinMap(coinList, coinMap);
		return coinMap;
	}

	private static void setCoinMap(List<Coin> coinList, Map<Integer, Integer> coinMap) {
		for (Coin coin : coinList) {
			coinMap.replace(coin.getAmount(), coinMap.get(coin.getAmount()) + 1);
		}
	}

	private static Map<Integer, Integer> getCoinMap() {
		Map<Integer, Integer> coinMap = new LinkedHashMap<>();
		for (Coin coin : Coin.values()) {
			coinMap.put(coin.getAmount(), 0);
		}
		return coinMap;
	}
}
