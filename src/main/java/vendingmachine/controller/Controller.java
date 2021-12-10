package vendingmachine.controller;

import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.domain.Item;
import vendingmachine.domain.Machine;
import vendingmachine.service.MachineService;
import vendingmachine.util.Parser;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class Controller {
	private final MachineService machineService = new MachineService();
	private final Parser parser = new Parser();

	public void run() {
		Machine machine = new Machine();
		setMachine(machine);
		purchaseItemsByUser(machine);
		printRestInputCoinAndExit(machine);
	}

	private void setMachine(Machine machine) {
		getUserInputAndSetMachineCoin(machine);
		setMachineItems(machine);
		inputCoinsByUser(machine);
	}

	private void printRestInputCoinAndExit(Machine machine) {
		OutputView.printInputCoin(machineService.getInputCoin(machine));
		OutputView.printLastMachineCoin(machine.getCoins());
	}

	private void purchaseItemsByUser(Machine machine) {
		while (machineService.isPossiblePurchase(machine)) {
			OutputView.printInputCoin(machineService.getInputCoin(machine));
			InputView.requestPurchaseItem();
			purchaseItem(machine);
		}
	}

	private void inputCoinsByUser(Machine machine) {
		InputView.requestInputCoin();
		machineService.addInputCoins(machine, getInputCoin());
	}

	private void setMachineItems(Machine machine) {
		InputView.requestMachineItem();
		machine.addItems(getItemList());
	}

	private void getUserInputAndSetMachineCoin(Machine machine) {
		InputView.requestMachineCoin();
		setMachineCoin(machine);
		OutputView.printFirstMachineCoin(machine.getCoins());
	}

	private void purchaseItem(Machine machine) {
		try {
			machineService.purchaseItem(machine, Console.readLine());
		} catch (IllegalArgumentException e) {
			OutputView.printExceptionMessage(e.getMessage());
			purchaseItem(machine);
		}
	}

	private Integer getInputCoin() {
		try {
			return parser.convertStringToInt(Console.readLine());
		} catch (IllegalArgumentException e) {
			OutputView.printExceptionMessage(e.getMessage());
			return getInputCoin();
		}
	}

	private List<Item> getItemList() {
		try {
			return parser.parseItemInput(Console.readLine());
		} catch (IllegalArgumentException e) {
			OutputView.printExceptionMessage(e.getMessage());
			return getItemList();
		}
	}

	private void setMachineCoin(Machine machine) {
		Integer machineCoin = getMachineCoin();
		try {
			machineService.addCoins(machine, machineCoin);
		} catch (IllegalArgumentException e) {
			OutputView.printExceptionMessage(e.getMessage());
			setMachineCoin(machine);
		}
	}

	private Integer getMachineCoin() {
		try {
			return parser.convertStringToInt(Console.readLine());
		} catch (IllegalArgumentException e) {
			OutputView.printExceptionMessage(e.getMessage());
			return getMachineCoin();
		}
	}
}
