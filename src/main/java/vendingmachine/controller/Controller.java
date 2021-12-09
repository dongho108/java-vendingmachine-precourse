package vendingmachine.controller;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.domain.Machine;
import vendingmachine.service.MachineService;
import vendingmachine.util.Parser;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class Controller {
	MachineService machineService = new MachineService();
	Parser parser = new Parser();

	public void run() {
		InputView.requestMachineCoin();
		Machine machine = new Machine();
		setMachine(machine);
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
