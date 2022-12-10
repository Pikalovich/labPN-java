package by.grsu.npikalovich.shop.web.dto;

import java.sql.Timestamp;

public class OrderDto {
	private Integer id;
	private Integer clientId;
	private Integer productId;
	private Integer deliveryAddressId;
	private Integer count;
	private Integer price;
	private String clientName;
	private String productName;
	private String deliveryAddressName;

	public Integer getId() {
		return id;
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

	public void setDeliveryAddressId(Integer deliveryAddressId) {
		this.deliveryAddressId = deliveryAddressId;
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

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDeliveryAddressName() {
		return deliveryAddressName;
	}

	public void setDeliveryAddressName(String deliveryAddressName) {
		this.deliveryAddressName = deliveryAddressName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
