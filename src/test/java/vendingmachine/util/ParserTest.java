package vendingmachine.util;

import static camp.nextstep.edu.missionutils.test.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import vendingmachine.domain.Item;

class ParserTest {
	private final Parser parser = new Parser();
	private static final String ERROR_MESSAGE = "[ERROR]";

	@Test
	public void 상품정상입력파싱() throws Exception {
		//given
		List<Item> itemList = parser.parseItemInput("[콜라,1500,20];[사이다,1000,10]");
		//when
		//then
		Assertions.assertThat("콜라").isEqualTo(itemList.get(0).getName());
		Assertions.assertThat(1500).isEqualTo(itemList.get(0).getPrice());
		Assertions.assertThat(20).isEqualTo(itemList.get(0).getQuantity());
		Assertions.assertThat("사이다").isEqualTo(itemList.get(1).getName());
		Assertions.assertThat(1000).isEqualTo(itemList.get(1).getPrice());
		Assertions.assertThat(10).isEqualTo(itemList.get(1).getQuantity());
	}

	@Test
	public void 상품입력_빈문자_예외처리() throws Exception {
		assertSimpleTest(() -> assertThatThrownBy(() -> parser.parseItemInput(""))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining(ERROR_MESSAGE));
	}

	@Test
	public void 상품입력_대괄호_예외처리() throws Exception {
		assertSimpleTest(() -> assertThatThrownBy(() -> parser.parseItemInput("콜라,1500,20"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining(ERROR_MESSAGE));
	}

	@Test
	public void 상품입력_상품정보불충분_예외처리() throws Exception {
		assertSimpleTest(() -> assertThatThrownBy(() -> parser.parseItemInput("[콜라,1500];[사이다,1000,10]"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining(ERROR_MESSAGE));
	}

	@Test
	public void 상품입력_상품정보타입_예외처리() throws Exception {
		assertSimpleTest(() -> assertThatThrownBy(() -> parser.parseItemInput("[콜라,천원,열개];[사이다,1000,10]"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining(ERROR_MESSAGE));
	}
}