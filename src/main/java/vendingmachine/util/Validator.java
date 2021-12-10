package vendingmachine.util;

import java.util.regex.Pattern;

public class Validator {
	private static final int MIN_COIN = 10;

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

	public void isItemInformation(String[] itemInfo) {
		validateItemInformationLength(itemInfo);
		validateIsEnglishOrKorean(itemInfo[0]);
		isInteger(itemInfo[1]);
		isInteger(itemInfo[2]);
	}

	private void validateItemInformationLength(String[] itemInfo) {
		if (itemInfo.length != 3) {
			throwIncorrectItemInformationException();
		}
	}

	private void validateIsEnglishOrKorean(String input) {
		if (!isEnglishOrKorean(input)) {
			throw new IllegalArgumentException("[ERROR] 상품이름은 영어 또는 한국어 또는 숫자로 구성되어야 합니다.");
		}
	}

	private boolean isEnglishOrKorean(String input) {
		return Pattern.matches("^[0-9a-zA-Z가-힣]*$", input);
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
