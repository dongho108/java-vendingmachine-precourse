package vendingmachine.util;

import java.util.ArrayList;
import java.util.List;

import vendingmachine.domain.Item;

public class Parser {
	private static final String SEMI_COLON = ";";
	private static final String LEFT_BRACKET = "[";
	private static final String RIGHT_BRACKET = "]";
	private static final String COMMA = ",";
	private Validator validator = new Validator();

	public Integer convertStringToInt(String input) {
		validator.isInteger(input);
		return Integer.parseInt(input);
	}

	public List<Item> parseItemInput(String input) {
		validator.isEmpty(input);
		String[] split = input.split(SEMI_COLON);
		List<Item> itemList = new ArrayList<>();
		for (String string : split) {
			validator.isInBracket(string);
			string = string.replace(LEFT_BRACKET, "").replace(RIGHT_BRACKET, "");
			String[] itemInfo = string.split(COMMA);
			validator.isItemInformation(itemInfo);
			itemList.add(new Item(itemInfo[0], convertStringToInt(itemInfo[1]), convertStringToInt(itemInfo[2])));
		}
		return itemList;
	}
}
