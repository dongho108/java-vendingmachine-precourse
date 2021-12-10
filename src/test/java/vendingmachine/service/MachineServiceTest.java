package vendingmachine.service;

import static camp.nextstep.edu.missionutils.test.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import vendingmachine.domain.Item;
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

	@Test
	public void 자판기_보유상품_생성() throws Exception {
		//given
		Machine machine = new Machine();
		//when
		List<Item> itemList = new ArrayList<>();
		itemList.add(new Item("콜라", 1500, 20));
		itemList.add(new Item("사이다", 1000, 10));
		machineService.addItem(machine, itemList);
		//then
		Assertions.assertThat("콜라").isEqualTo(itemList.get(0).getName());
		Assertions.assertThat(1500).isEqualTo(itemList.get(0).getPrice());
		Assertions.assertThat(20).isEqualTo(itemList.get(0).getQuantity());
		Assertions.assertThat("사이다").isEqualTo(itemList.get(1).getName());
		Assertions.assertThat(1000).isEqualTo(itemList.get(1).getPrice());
		Assertions.assertThat(10).isEqualTo(itemList.get(1).getQuantity());
	}

	@Test
	public void 자판기_상품_정상구매() throws Exception {
		//given
		Machine machine = new Machine();
		machineService.addInputCoins(machine, 2000);
		List<Item> itemList = new ArrayList<>();
		itemList.add(new Item("콜라", 1500, 20));
		itemList.add(new Item("사이다", 1000, 10));
		machineService.addItem(machine, itemList);
		//when
		machineService.purchaseItem(machine, "콜라");
		//then
		Assertions.assertThat(machine.getItem("콜라").getQuantity()).isEqualTo(19);
	}
}