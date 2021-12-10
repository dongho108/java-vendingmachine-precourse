package vendingmachine.view;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import vendingmachine.domain.Coin;

public class OutputView {
	public static void printExceptionMessage(String message) {
		System.out.println(message);
	}

	public static void printLastMachineCoin(List<Coin> coinList) {
		System.out.println("잔돈");
		Map<Integer, Integer> sortedCoinMap = getSortedCoinMap(coinList);
		for (Integer integer : sortedCoinMap.keySet()) {
			if (sortedCoinMap.get(integer) == 0) {
				continue;
			}
			System.out.println(integer + "원 - " + sortedCoinMap.get(integer) + "개");
		}
	}

	public static void printFirstMachineCoin(List<Coin> coinList) {
		System.out.println();
		System.out.println("자판기가 보유한 동전");
		Map<Integer, Integer> sortedCoinMap = getSortedCoinMap(coinList);
		for (Integer integer : sortedCoinMap.keySet()) {
			System.out.println(integer + "원 - " + sortedCoinMap.get(integer) + "개");
		}
		System.out.println();
	}

	public static void printInputCoin(Integer inputCoin) {
		System.out.println();
		System.out.println("투입 금액: " + inputCoin + "원");
	}

	private static Map<Integer, Integer> getSortedCoinMap(List<Coin> coinList) {
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
