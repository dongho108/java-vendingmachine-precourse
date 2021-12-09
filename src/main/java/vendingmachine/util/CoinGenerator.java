package vendingmachine.util;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.domain.Coin;

public class CoinGenerator {

	public static List<Coin> generate(int price) throws IllegalArgumentException{
		Validator.validatePossibleCoin(price);
		List<Coin> coinList = new ArrayList<>();
		List<Integer> numbers = getNumbers();
		generateCoin(price, coinList, numbers);
		return coinList;
	}

	private static void generateCoin(int price, List<Coin> coinList, List<Integer> numbers) {
		while (price > 0) {
			int RandomCoin = Randoms.pickNumberInList(numbers);
			if (price < RandomCoin) {
				continue;
			}
			coinList.add(Coin.getThisAmountCoin(RandomCoin));
			price -= RandomCoin;
		}
	}

	private static List<Integer> getNumbers() {
		List<Integer> numbers = new ArrayList<>();
		for (Coin coin : Coin.values()) {
			numbers.add(coin.getAmount());
		}
		return numbers;
	}
}
