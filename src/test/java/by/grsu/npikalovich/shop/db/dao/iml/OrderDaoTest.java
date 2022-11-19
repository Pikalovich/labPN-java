package by.grsu.npikalovich.shop.db.dao.iml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.npikalovich.shop.db.dao.IDao;
import by.grsu.npikalovich.shop.db.dao.impl.AddressDaoImpl;
import by.grsu.npikalovich.shop.db.dao.impl.ClientDaoImpl;
import by.grsu.npikalovich.shop.db.dao.impl.OrderDaoImpl;
import by.grsu.npikalovich.shop.db.dao.impl.ProductDaoImpl;
import by.grsu.npikalovich.shop.db.model.Product;
import by.grsu.npikalovich.shop.db.model.Order;
import by.grsu.npikalovich.shop.db.model.Address;
import by.grsu.npikalovich.shop.db.model.Client;

public class OrderDaoTest extends AbstractTest {
	private static final IDao<Integer, Order> orderDao = OrderDaoImpl.INSTANCE;
	private static final IDao<Integer, Product> productDao = ProductDaoImpl.INSTANCE;
	private static final IDao<Integer, Client> clientDao = ClientDaoImpl.INSTANCE;
	private static final IDao<Integer, Address> teamDao = AddressDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Order entity = new Order();
		entity.setClientId(saveClient(4).getId());
		entity.setDeliveryAdressId (saveDeliveryAdress(4).getId());
		entity.setCount(4);
		entity.setPrice(40);
		entity.setProductId(saveDeliveryAdress(4).getId());
		orderDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	private Product saveDeliveryAdress(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	private Product saveClient(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Test
	public void testUpdate() {
		Order entity = new Order();
		entity.setClientId(saveClient(4).getId());
		entity.setDeliveryAdressId (saveDeliveryAdress(4).getId());
		entity.setCount(4);
		entity.setPrice(40);
		entity.setProductId(saveDeliveryAdress(4).getId());
		orderDao.insert(entity);
		
		
		Integer newDeliveryAdressId = 7;
		entity.setDeliveryAdressId(newDeliveryAdressId);
		entity.setClientId(4);
		entity.setCount(4);
		entity.setPrice(40);
		entity.setProductId(4);
		orderDao.insert(entity);

		
		Order updatedEntity = orderDao.getById(entity.getId());
		Assertions.assertEquals(newDeliveryAdressId, updatedEntity.getDeliveryAdressId());
		Assertions.assertEquals(4, updatedEntity.getPrice());
	}

	@Test
	public void testDelete() {
		Order entity = new Order();
		entity.setClientId(4);
		entity.setDeliveryAdressId(4);
		entity.setCount(4);
		entity.setPrice(40);
		entity.setProductId(4);
		orderDao.insert(entity);

		orderDao.delete(entity.getId());

		Assertions.assertNull(orderDao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Order entity = new Order();
		entity.setClientId(4);
		entity.setDeliveryAdressId(4);
		entity.setCount(4);
		entity.setPrice(40);
		entity.setProductId(4);
		orderDao.insert(entity);

		Order selectedEntity = orderDao.getById(entity.getId());

		Assertions.assertEquals(entity.getClientId(), selectedEntity.getClientId());
		Assertions.assertEquals(entity.getDeliveryAdressId(), selectedEntity.getDeliveryAdressId());
		Assertions.assertEquals(entity.getCount(), selectedEntity.getCount());
		Assertions.assertEquals(entity.getPrice(), selectedEntity.getPrice());
		Assertions.assertEquals(entity.getProductId(), selectedEntity.getProductId());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Order entity = new Order();
			entity.setClientId(4);
			entity.setDeliveryAdressId(4+i);
			entity.setCount(4);
			entity.setPrice(40);
			entity.setProductId(4);
			orderDao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, orderDao.getAll().size());
	}

private Product saveProduct() {
	Product entity = new Product();
	entity.setTitle("title");
	entity.setPrice(80);
	entity.setDescription("description");
	productDao.insert(entity);
	return entity;
}

private Client saveClient() {
	Client entity = new Client();
	entity.setFirstName("P");
	entity.setLastName("N");
	entity.setEmail("email");
	entity.setTime(1601);
	clientDao.insert(entity);
	return entity;
	}

	private Address saveAddress() {
	Address entity = new Address();
	entity.setTown("Grodno");
	entity.setHouse(5);
	entity.setFlat(13);
	entity.setStreet("Gaspadarchaya");
	addressDao.insert(entity);
	return entity;
}
}

