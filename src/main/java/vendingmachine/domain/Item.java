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
		this.quantity -= quantity;
	}

	public Boolean isSoldOut() {
		return quantity == 0;
	}
}
