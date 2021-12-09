package vendingmachine.util;

public class Validator {
	private static final int MIN_COIN = 10;

	public static void validatePossibleCoin(int price) {
		if (!isPossibleCoin(price)) {
			throw new IllegalArgumentException("[ERROR] 동전으로 만들 수 있는 금액을 입력해주세요.");
		}
	}

	private static boolean isPossibleCoin(int price) {
		return price % MIN_COIN == 0;
	}
}
