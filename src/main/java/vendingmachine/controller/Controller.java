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

		//자판기 객체 생성
		Machine machine = new Machine();

		// 자판기 보유잔돈 세팅
		InputView.requestMachineCoin();
		setMachine(machine);
		OutputView.printFirstMachineCoin(machine.getCoins());

		// 자판기 상품세팅
		InputView.requestMachineItem();
		machine.addItems(getItemList());

		// 사용자의 동전투입
		InputView.requestInputCoin();
		machineService.addInputCoins(machine, getInputCoin());

		// 상품구매
		while (machineService.isPossiblePurchase(machine)) {
			OutputView.printInputCoin(machineService.getInputCoin(machine));
			InputView.requestPurchaseItem();
			purchaseItem(machine);
		}

		// 구매종료
		OutputView.printInputCoin(machineService.getInputCoin(machine));
		OutputView.printLastMachineCoin(machine.getCoins());
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

	private void setMachine(Machine machine) {
		Integer machineCoin = getMachineCoin();
		try {
			machineService.addCoins(machine, machineCoin);
		} catch (IllegalArgumentException e) {
			OutputView.printExceptionMessage(e.getMessage());
			setMachine(machine);
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
