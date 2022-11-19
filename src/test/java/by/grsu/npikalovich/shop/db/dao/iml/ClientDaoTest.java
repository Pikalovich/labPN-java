package by.grsu.npikalovich.shop.db.dao.iml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.npikalovich.shop.db.dao.IDao;
import by.grsu.npikalovich.shop.db.dao.impl.ClientDaoImpl;
import by.grsu.npikalovich.shop.db.model.Client;

public class ClientDaoTest extends AbstractTest {
	private static final IDao<Integer, Client> dao = ClientDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Client entity = new Client();
		entity.setFirstName("P");
		entity.setLastName("N");
		entity.setEmail("email");
		entity.setTime(1601);
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Client entity = new Client();
		entity.setFirstName("P");
		entity.setLastName("N");
		entity.setEmail("email");
		entity.setTime(1601);
		dao.insert(entity);
		
		
		String newFirstName = "jk";
		String newLastName = "rk";
		String newEmail = "sk";
		entity.setFirstName(newFirstName);
		entity.setLastName(newLastName);
		entity.setEmail(newEmail);
		entity.setTime(1601);
		dao.update(entity);

		
		Client updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals(newFirstName, updatedEntity.getFirstName());
		Assertions.assertEquals(newLastName, updatedEntity.getLastName());
		Assertions.assertEquals(newEmail, updatedEntity.getEmail());
		Assertions.assertEquals(1601, updatedEntity.getTime());
	}

	@Test
	public void testDelete() {
		Client entity = new Client();
		entity.setFirstName("P");
		entity.setLastName("N");
		entity.setEmail("email");
		entity.setTime(1601);
		dao.insert(entity);

		dao.delete(entity.getId());

		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Client entity = new Client();
		entity.setFirstName("P");
		entity.setLastName("N");
		entity.setEmail("email");
		entity.setTime(1601);
		dao.insert(entity);

		Client selectedEntity = dao.getById(entity.getId());

		Assertions.assertEquals(entity.getFirstName(), selectedEntity.getFirstName());
		Assertions.assertEquals(entity.getLastName(), selectedEntity.getLastName());
		Assertions.assertEquals(entity.getEmail(), selectedEntity.getEmail());
		Assertions.assertEquals(entity.getTime(), selectedEntity.getTime());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
        Client entity = new Client();
			entity.setFirstName("FM" + i); // generate some random meaningless name as it is just a test (the data can be unreal)
			entity.setLastName("description");
			entity.setEmail("email");
			entity.setTime(1611);
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}
}

