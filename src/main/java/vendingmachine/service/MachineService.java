package vendingmachine.service;

import java.util.Map;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Item;
import vendingmachine.domain.Machine;
import vendingmachine.repository.MachineRepository;
import vendingmachine.util.CoinGenerator;

public class MachineService {
	MachineRepository machineRepository = new MachineRepository();

	public Long generate() {
		return machineRepository.generate();
	}

	public Machine findById(Long id) {
		return machineRepository.findById(id);
	}

	public void addCoins(Long id, Integer totalCoin) {
		Machine machine = machineRepository.findById(id);
		Map<Coin, Integer> generatedCoins = CoinGenerator.generate(Coin.getCoinList(), totalCoin);
		machine.addCoins(generatedCoins);
	}

	public void addInputCoins(Long id, Integer amount) {
		Machine machine = machineRepository.findById(id);
		machine.addInputCoinAmount(amount);
	}

	public void addItems(Long id, Map<String, Item> items) {
		Machine machine = machineRepository.findById(id);
		machine.addItems(items);
	}

	public void purchase(Long id, String itemName) throws IllegalArgumentException {
		Machine machine = machineRepository.findById(id);
		machine.purchase(itemName);
	}

	public Boolean isPurchasable(Long id) {
		Machine machine = machineRepository.findById(id);
		return machine.isPurchasable();
	}

	public Map<Coin, Integer> returnCoins(Long id) {
		Machine machine = machineRepository.findById(id);
		return machine.returnCoins();
	}
}
