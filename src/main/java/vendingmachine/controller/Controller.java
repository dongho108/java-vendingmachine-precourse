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
		OutputView.printHeadRestMachineCoin();
		OutputView.printMachineCoin(machineService.getReturnInputCoin(machine));
	}

	private void purchaseItemsByUser(Machine machine) {
		while (machineService.isPossiblePurchase(machine)) {
			OutputView.printInputCoin(machineService.getInputCoin(machine));
			InputView.requestPurchaseItem();
			purchaseItemAndPrintException(machine);
		}
	}

	private void inputCoinsByUser(Machine machine) {
		InputView.requestInputCoin();
		machineService.addInputCoins(machine, getInputCoinAndPrintException());
	}

	private void setMachineItems(Machine machine) {
		InputView.requestMachineItem();
		machine.addItems(getItemListAndPrintException());
	}

	private void getUserInputAndSetMachineCoin(Machine machine) {
		InputView.requestMachineCoin();
		setMachineCoinAndPrintException(machine);
		OutputView.printHeadMachineCoin();
		OutputView.printMachineCoin(machineService.getMachineCoin(machine));
	}

	private void purchaseItemAndPrintException(Machine machine) {
		try {
			machineService.purchaseItem(machine, Console.readLine());
		} catch (IllegalArgumentException e) {
			OutputView.printExceptionMessage(e.getMessage());
			purchaseItemAndPrintException(machine);
		}
	}

	private Integer getInputCoinAndPrintException() {
		try {
			return parser.convertStringToInt(Console.readLine());
		} catch (IllegalArgumentException e) {
			OutputView.printExceptionMessage(e.getMessage());
			return getInputCoinAndPrintException();
		}
	}

	private List<Item> getItemListAndPrintException() {
		try {
			return parser.parseItemInput(Console.readLine());
		} catch (IllegalArgumentException e) {
			OutputView.printExceptionMessage(e.getMessage());
			return getItemListAndPrintException();
		}
	}

	private void setMachineCoinAndPrintException(Machine machine) {
		Integer machineCoin = getMachineCoinAndPrintException();
		try {
			machineService.addCoins(machine, machineCoin);
		} catch (IllegalArgumentException e) {
			OutputView.printExceptionMessage(e.getMessage());
			setMachineCoinAndPrintException(machine);
		}
	}

	private Integer getMachineCoinAndPrintException() {
		try {
			return parser.convertStringToInt(Console.readLine());
		} catch (IllegalArgumentException e) {
			OutputView.printExceptionMessage(e.getMessage());
			return getMachineCoinAndPrintException();
		}
	}
}
