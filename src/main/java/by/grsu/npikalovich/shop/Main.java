package by.grsu.npikalovich.shop;
import  java.sql.Timestamp;

import by.grsu.npikalovich.shop.db.model.Order;
import by.grsu.npikalovich.shop.db.model.Address;
import by.grsu.npikalovich.shop.db.model.Client;
import by.grsu.npikalovich.shop.db.model.Product;

public class Main {
	
	public static void main (String[] args) {
		
		Order order=new Order();
		order.setId(1) ;
		order.setClientId(2);
		order.setProductId (6);
		order.setDeliveryAdressId("Гаспадарчая 23");
		order.setCount (1);
		order.setPrice(80);
		System.out.println(order.toString());
		
		
		Address adress=new Address();
		adress.setId(1) ;
		adress.setTown("Гродно");
		adress.setStreet ("Гаспадарчая");
		adress.setHouse (23);
		adress.setFlat(211);
		System.out.println(adress.toString());
		
		
		
		Client client=new Client();
		client.setId(1) ;
		client.setFirstName("Иванов");
		client.setLastName ("Иван");
		client.setEmail("ivanov@mai.ru");
		client.setTime(2022);
		System.out.println(client.toString());
		
		
		Product product=new Product();
		product.setId(1) ;
		product.setTitle("Набор");
		product.setPrice (80);
		product.setDescription ("20 ягод");
		System.out.println(product.toString());
		
	}
}
