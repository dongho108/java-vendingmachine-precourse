package vendingmachine.view;

import java.util.Map;

public class OutputView {
	public static void printExceptionMessage(String message) {
		System.out.println(message);
	}

	public static void printHeadMachineCoin() {
		System.out.println();
		System.out.println("자판기가 보유한 동전");
	}

	public static void printHeadRestMachineCoin() {
		System.out.println("잔돈");
	}

	public static void printMachineCoin(Map<Integer, Integer> sortedCoinMap) {
		for (Integer integer : sortedCoinMap.keySet()) {
			System.out.println(integer + "원 - " + sortedCoinMap.get(integer) + "개");
		}
		System.out.println();
	}

	public static void printInputCoin(Integer inputCoin) {
		System.out.println();
		System.out.println("투입 금액: " + inputCoin + "원");
	}
}
