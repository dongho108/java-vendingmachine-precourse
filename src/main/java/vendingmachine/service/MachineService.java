package vendingmachine.service;

import java.util.List;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Item;
import vendingmachine.domain.Machine;
import vendingmachine.util.CoinGenerator;

public class MachineService {
	public void addCoins(Machine machine, int price) {
		List<Coin> coinList = CoinGenerator.generate(price);
		machine.addCoins(coinList);
	}

	public void addItem(Machine machine, List<Item> itemList) {
		machine.addItems(itemList);
	}
}
