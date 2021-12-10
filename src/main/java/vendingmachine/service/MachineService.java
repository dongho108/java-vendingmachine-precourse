package vendingmachine.service;

import java.util.List;
import java.util.Map;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Item;
import vendingmachine.domain.Machine;
import vendingmachine.util.CoinGenerator;

public class MachineService {
	CoinGenerator coinGenerator = new CoinGenerator();

	public void addCoins(Machine machine, int amount) {
		List<Coin> coinList = coinGenerator.generate(amount);
		machine.addCoins(coinList);
	}

	public void addItem(Machine machine, List<Item> itemList) {
		machine.addItems(itemList);
	}

	public void addInputCoins(Machine machine, int amount) {
		machine.addInputCoins(amount);
	}

	public Map<Integer, Integer> getMachineCoin(Machine machine) {
		return machine.getCoins();
	}

	public Integer getInputCoin(Machine machine) {
		return machine.getInputCoins();
	}

	public Map<Integer, Integer> getRestInputCoin(Machine machine) {
		return machine.getRestInputCoin();
	}

	public Boolean isPossiblePurchase(Machine machine) {
		return machine.isPossiblePurchase();
	}

	public void purchaseItem(Machine machine, String itemName) {
		if (!machine.isItemIn(itemName)) {
			throw new IllegalArgumentException("[ERROR] 상품이 없습니다. 다른 상품을 선택해주세요.");
		}
		Item item = machine.getItem(itemName);
		if (!machine.isPossiblePurchaseItem(item)) {
			throw new IllegalArgumentException("[ERROR] 투입 금액이 부족합니다. 다른 상품을 선택해 주세요.");
		}
		item.reduceQuantity();
		machine.reduceInputCoin(item.getPrice());
	}
}
