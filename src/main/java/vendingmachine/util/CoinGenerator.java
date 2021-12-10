package vendingmachine.util;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.domain.Coin;

public class CoinGenerator {
	private final Validator validator = new Validator();

	public List<Coin> generate(int price) throws IllegalArgumentException {
		validator.isPossibleCoin(price);
		return generateCoin(price, getNumbers());
	}

	private List<Coin> generateCoin(int price, List<Integer> numbers) {
		List<Coin> coinList = new ArrayList<>();
		while (price > 0) {
			int RandomCoin = Randoms.pickNumberInList(numbers);
			if (price < RandomCoin) {
				continue;
			}
			coinList.add(Coin.getThisAmountCoin(RandomCoin));
			price -= RandomCoin;
		}
		return coinList;
	}

	private static List<Integer> getNumbers() {
		List<Integer> numbers = new ArrayList<>();
		for (Coin coin : Coin.values()) {
			numbers.add(coin.getAmount());
		}
		return numbers;
	}
}
