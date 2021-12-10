package vendingmachine.util;

public class Validator {
	private static final int MIN_COIN = 10;
	private static final int MIN_ITEM_VALUE = 100;
	private static final int STANDARD_DIVISIBLE = 10;

	public void isPossibleCoin(int price) {
		if (!checkPossibleCoin(price)) {
			throw new IllegalArgumentException("[ERROR] 동전으로 만들 수 있는 금액을 입력해주세요.");
		}
	}

	public void isInteger(String input) {
		try {
			Integer.parseInt(input);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("[ERROR] 숫자를 입력해 주세요");
		}
	}

	public void isRightItemInformation(String[] itemInfo) {
		validateItemInformationLength(itemInfo);
		isInteger(itemInfo[1]);
		isInteger(itemInfo[2]);
	}

	public void isRightItemPrice(Integer itemPrice) {
		isOverMinPrice(itemPrice);
		isDivisible(itemPrice);
	}

	private void isDivisible(Integer itemPrice) {
		if (itemPrice % STANDARD_DIVISIBLE != 0) {
			throw new IllegalArgumentException("[ERROR] 상품 가격은 10원 단위로 나누어떨어져야 합니다.");
		}
	}

	private void isOverMinPrice(Integer itemPrice) {
		if (itemPrice < MIN_ITEM_VALUE) {
			throw new IllegalArgumentException("[ERROR] 상품 가격은 100원 이상이어야 합니다.");
		}
	}

	private void validateItemInformationLength(String[] itemInfo) {
		if (itemInfo.length != 3) {
			throwIncorrectItemInformationException();
		}
	}

	private static boolean checkPossibleCoin(int price) {
		return price % MIN_COIN == 0;
	}

	public void isEmpty(String input) {
		if (input.isEmpty()) {
			throw new IllegalArgumentException("[ERROR] 빈 문자열입니다.");
		}
	}

	public void isInBracket(String string) {
		if (string.indexOf('[') != 0) {
			throwIncorrectItemInformationException();
		}
		if (string.lastIndexOf(']') != string.length() - 1) {
			throwIncorrectItemInformationException();
		}
	}

	private void throwIncorrectItemInformationException() {
		throw new IllegalArgumentException("[ERROR] 상품정보를 제대로 입력해주세요. 입력예시) [콜라,1500,20];[사이다,1000,10]");
	}
}
