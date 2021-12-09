package vendingmachine.service;

import java.util.List;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Item;
import vendingmachine.domain.Machine;
import vendingmachine.util.CoinGenerator;

public class MachineService {
	public void addCoins(Machine machine, int amount) {
		List<Coin> coinList = CoinGenerator.generate(amount);
		machine.addCoins(coinList);
	}

	public void addItem(Machine machine, List<Item> itemList) {
		machine.addItems(itemList);
	}

	public void addInputCoins(Machine machine, int amount) {
		machine.addInputCoins(amount);
	}

	public Boolean soldOut(Machine machine) {
		return machine.isSoldOutAllItems();
	}

	public List<Coin> getRestCoin(Machine machine) {
		return machine.getCoins();
	}

	public Boolean isPossiblePurchase(Machine machine) {
		return machine.isPossiblePurchase();
	}

	public void purchaseItem(Machine machine, String itemName, int quantity) {
		if (!machine.isItemIn(itemName)) {
			throw new IllegalArgumentException("[ERROR] 상품이 없습니다. 다른 상품을 선택해주세요.");
		}
		Item item = machine.getItem(itemName);
		if (!machine.isPossiblePurchaseItem(item)) {
			throw new IllegalArgumentException("[ERROR] 투입 금액이 부족합니다. 다른 상품을 선택해 주세요.");
		}
		machine.purchaseItem(item, quantity);
	}
}
