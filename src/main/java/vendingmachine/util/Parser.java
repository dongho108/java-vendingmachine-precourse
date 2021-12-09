package vendingmachine.util;

public class Parser {
	Validator validator = new Validator();

	public Integer convertStringToInt(String input) {
		validator.validateInputCoin(input);
		return Integer.parseInt(input);
	}
}
