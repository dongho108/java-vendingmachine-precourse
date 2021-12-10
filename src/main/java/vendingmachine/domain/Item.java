package vendingmachine.domain;

public class Item {
	private final String name;
	private final Integer price;
	private Integer quantity;
	private static final int MIN_QUANTITY = 1;
	private static final int PURCHASE_QUANTITY = 1;

	public Item(String name, Integer price, Integer quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public Integer getPrice() {
		return price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void reduceQuantity() {
		if (this.quantity < MIN_QUANTITY) {
			throw new IllegalArgumentException("[ERROR] 수량이 부족합니다.");
		}
		this.quantity -= PURCHASE_QUANTITY;
	}

	public Boolean isSoldOut() {
		return quantity == 0;
	}
}
