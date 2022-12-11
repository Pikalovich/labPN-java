package by.grsu.npikalovich.shop.web.context;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.grsu.npikalovich.shop.db.dao.AbstractDao;
import by.grsu.npikalovich.shop.db.dao.IDao;
import by.grsu.npikalovich.shop.db.dao.impl.ClientDaoImpl;
import by.grsu.npikalovich.shop.db.dao.impl.OrderDaoImpl;
import by.grsu.npikalovich.shop.db.dao.impl.AddressDaoImpl;
import by.grsu.npikalovich.shop.db.dao.impl.ProductDaoImpl;
import by.grsu.npikalovich.shop.db.model.Product;
import by.grsu.npikalovich.shop.db.model.Client;
import by.grsu.npikalovich.shop.db.model.Order;
import by.grsu.npikalovich.shop.db.model.Address;

public class AppStartupListener implements ServletContextListener {
	private static final IDao<Integer, Product> productDao = ProductDaoImpl.INSTANCE;
	private static final IDao<Integer, Order> orderDao = OrderDaoImpl.INSTANCE;
	private static final IDao<Integer, Client> clientDao = ClientDaoImpl.INSTANCE;
	private static final IDao<Integer, Address> addressDao = AddressDaoImpl.INSTANCE;

	private static final String DB_NAME = "production-db";

	private void initDb() throws SQLException {
		AbstractDao.init(DB_NAME);
		if (!AbstractDao.isDbExist()) {
			System.out.println(String.format("DB '%s' doesn't exist and will be created", DB_NAME));
			AbstractDao.createDbSchema();
			loadInitialData();
		} else {
			System.out.println(String.format("DB '%s' exists", DB_NAME));
		}
	}

	private void loadInitialData() {
		Product productEntity = new Product();
		productEntity.setTitle("Meddi");
		productEntity.setPrice(80);
		productEntity.setDescription("description");
		productDao.insert(productEntity);
		System.out.println("created: " + productEntity);

		Order orderEntity = new Order();
		orderEntity.setClientId(24);
		orderEntity.setDeliveryAddressId(54);
		orderEntity.setProductId(44);
		orderEntity.setCount(4);
		orderEntity.setPrice(444);
		orderDao.insert(orderEntity);
		System.out.println("created: " + orderEntity);

		Address addressEntity = new Address();
		addressEntity.setFlat(13);
		addressEntity.setHouse(3);
		addressEntity.setStreet("Gaspadarchaya");
		addressEntity.setTown("Grodno");
		addressDao.insert(addressEntity);
		System.out.println("created: " + addressEntity);

		Client clientEntity = new Client();
		clientEntity.setFirstName("A");
		clientEntity.setLastName("B");
		clientEntity.setEmail("ab");
		clientEntity.setTime(21);
		clientDao.insert(clientEntity);
		System.out.println("created: " + clientEntity);
		System.out.println("initial data created");
	}

	private Timestamp getCurrentTime() {
		return new Timestamp(new Date().getTime());
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialized");
		try {
			initDb();
		} catch (SQLException e) {
			throw new RuntimeException("can't init DB", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed");
	}
}
