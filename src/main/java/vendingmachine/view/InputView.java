package vendingmachine.view;

public class InputView {
	public static void requestMachineCoin() {
		System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
	}

	public static void requestMachineItem() {
		System.out.println("상품명과 가격, 수량을 입력해 주세요.");
	}

	public static void requestInputCoin() {
		System.out.println();
		System.out.println("투입 금액을 입력해 주세요.");
	}

	public static void requestPurchaseItem() {
		System.out.println("구매할 상품명을 입력해 주세요.");
	}
}
