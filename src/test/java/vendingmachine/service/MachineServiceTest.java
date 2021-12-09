package vendingmachine.service;

import static camp.nextstep.edu.missionutils.test.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import vendingmachine.domain.Machine;

class MachineServiceTest {
	MachineService machineService = new MachineService();
	private static final String ERROR_MESSAGE = "[ERROR]";

	@Test
	void 자판기_동전생성() {
		Machine machine = new Machine();
		machineService.addCoins(machine, 450);
		machine.printCoins();
		Assertions.assertThat(450).isEqualTo(machine.getTotalCoinAmount());
	}

	@Test
	public void 동전으로_만들수없는_금액() throws Exception {
		//given
		Machine machine = new Machine();
		//when
		assertSimpleTest(() -> assertThatThrownBy(() -> machineService.addCoins(machine, 123))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining(ERROR_MESSAGE));
		//then
	}
}