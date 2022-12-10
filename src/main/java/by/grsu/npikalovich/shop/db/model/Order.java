package by.grsu.npikalovich.shop.db.model;

public class Order {
	private Integer id;
	private Integer clientId;
	private Integer productId;
	private Integer deliveryAddressId;
	private Integer count;
	private Integer price;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getDeliveryAddressId() {
		return deliveryAddressId;
	}

	public void setDeliveryAddressId(Integer newDeliveryAddressId) {
		this.deliveryAddressId = newDeliveryAddressId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "order [id=" + id + ", clientId=" + clientId + ", productId=" + productId + ", deliveryAdressId="
				+ deliveryAddressId + ", count=" + count + ", price=" + price + "]";
	}

}
