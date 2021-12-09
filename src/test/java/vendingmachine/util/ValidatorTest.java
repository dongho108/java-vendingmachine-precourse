package vendingmachine.util;

import static camp.nextstep.edu.missionutils.test.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import vendingmachine.domain.Machine;

class ValidatorTest {
	private static final String ERROR_MESSAGE = "[ERROR]";
	private final Validator validator = new Validator();

	@Test
	public void 문자입력() throws Exception {
		//given
		Machine machine = new Machine();
		//when
		assertSimpleTest(() -> assertThatThrownBy(() -> validator.validateInputCoin("a"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining(ERROR_MESSAGE));
		//then
	}

}