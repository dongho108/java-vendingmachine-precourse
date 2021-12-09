package vendingmachine.domain;

public class Item {
	private final String name;
	private final Integer price;
	private Integer quantity;

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

	public void reduceQuantity(int quantity) {
		if (this.quantity < quantity) {
			throw new IllegalArgumentException("[ERROR] 수량이 부족합니다.");
		}
		this.quantity -= quantity;
	}

	public Boolean isSoldOut() {
		return quantity == 0;
	}
}
