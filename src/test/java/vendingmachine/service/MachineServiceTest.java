package vendingmachine.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import vendingmachine.domain.Machine;

class MachineServiceTest {
	MachineService machineService = new MachineService();

	@Test
	void 자판기_동전생성() {
		Machine machine = new Machine();
		machineService.addCoins(machine, 450);
		machine.printCoins();
		Assertions.assertThat(450).isEqualTo(machine.getTotalCoinAmount());
	}
}