package by.grsu.npikalovich.shop.db.model;

public class Order {
	    private Integer id ;
	    private Integer clientId;
	    private Integer productId;
	    private String deliveryAdressId;	
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
		public String getDeliveryAdressId() {
			return deliveryAdressId;
		}
		public void setDeliveryAdressId(String newDeliveryAdressId) {
			this.deliveryAdressId = newDeliveryAdressId;
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
					+ deliveryAdressId + ", count=" + count + ", price=" + price + "]";
		}   
	    
	 
		
		
}
