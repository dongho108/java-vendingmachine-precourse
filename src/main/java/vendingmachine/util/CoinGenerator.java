package vendingmachine.util;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.domain.Coin;

public class CoinGenerator {

	public static List<Coin> generate(int price) {
		List<Coin> coinList = new ArrayList<>();
		List<Integer> numbers = new ArrayList<>();
		for (Coin coin : Coin.values()) {
			numbers.add(coin.getAmount());
		}
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
}
