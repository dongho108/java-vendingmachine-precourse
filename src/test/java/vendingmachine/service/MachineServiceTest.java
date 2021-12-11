package vendingmachine.service;

import static camp.nextstep.edu.missionutils.test.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Item;
import vendingmachine.domain.Machine;

class MachineServiceTest {
	private final MachineService machineService = new MachineService();
	private static final String ERROR_MESSAGE = "[ERROR]";

	@Test
	void 자판기_동전생성() {
		Machine machine = new Machine();
		machineService.addCoins(machine, 450);
		Assertions.assertThat(450).isEqualTo(machine.getTotalCoinAmount());
	}

	@Test
	public void 동전으로_만들수없는_금액() {
		Machine machine = new Machine();
		assertSimpleTest(() -> assertThatThrownBy(() -> machineService.addCoins(machine, 123))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining(ERROR_MESSAGE));
	}

	@Test
	public void 자판기_보유상품_생성() {
		Machine machine = new Machine();
		List<Item> itemList = new ArrayList<>();
		itemList.add(new Item("콜라", 1500, 20));
		itemList.add(new Item("사이다", 1000, 10));
		machineService.addItem(machine, itemList);
		Assertions.assertThat("콜라").isEqualTo(itemList.get(0).getName());
		Assertions.assertThat(1500).isEqualTo(itemList.get(0).getPrice());
		Assertions.assertThat(20).isEqualTo(itemList.get(0).getQuantity());
		Assertions.assertThat("사이다").isEqualTo(itemList.get(1).getName());
		Assertions.assertThat(1000).isEqualTo(itemList.get(1).getPrice());
		Assertions.assertThat(10).isEqualTo(itemList.get(1).getQuantity());
	}

	@Test
	public void 자판기_상품_정상구매() {
		Machine machine = getMachine();
		machineService.purchaseItem(machine, "콜라");
		Assertions.assertThat(machine.getItem("콜라").getQuantity()).isEqualTo(0);
	}

	@Test
	public void 자판기_상품_없는상품입력_예외() {
		Machine machine = getMachine();
		assertSimpleTest(() -> assertThatThrownBy(() -> machineService.purchaseItem(machine, "맥주"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining(ERROR_MESSAGE));
	}

	@Test
	public void 자판기_상품_수량부족_예외() {
		Machine machine = getMachine();
		machineService.purchaseItem(machine, "콜라");
		assertSimpleTest(() -> assertThatThrownBy(() -> machineService.purchaseItem(machine, "콜라"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining(ERROR_MESSAGE));
	}

	@Test
	public void 투입금액보다_잔돈이_많을때() {
		Machine machine = getMachine();
		machineService.purchaseItem(machine, "콜라");
		machineService.purchaseItem(machine, "사이다");
		int result = 0;
		Map<Integer, Integer> restInputCoin = machine.getReturnInputCoin();
		for (Integer coin : restInputCoin.keySet()) {
			result += (coin * restInputCoin.get(coin));
		}
		Assertions.assertThat(result).isEqualTo(500);
	}

	private Machine getMachine() {
		Machine machine = new Machine();
		machine.addCoins(getCoins());
		machineService.addInputCoins(machine, 3000);
		List<Item> itemList = new ArrayList<>();
		itemList.add(new Item("콜라", 1500, 1));
		itemList.add(new Item("사이다", 1000, 10));
		machineService.addItem(machine, itemList);
		return machine;
	}

	private List<Coin> getCoins() {
		List<Coin> coins = new ArrayList<>();
		coins.add(Coin.COIN_500);
		coins.add(Coin.COIN_500);
		coins.add(Coin.COIN_100);
		coins.add(Coin.COIN_100);
		coins.add(Coin.COIN_100);
		coins.add(Coin.COIN_100);
		coins.add(Coin.COIN_100);
		coins.add(Coin.COIN_50);
		coins.add(Coin.COIN_10);
		return coins;
	}
}