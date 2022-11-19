package by.grsu.npikalovich.shop.db.dao.iml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.npikalovich.shop.db.dao.IDao;
import by.grsu.npikalovich.shop.db.dao.impl.ProductDaoImpl;
import by.grsu.npikalovich.shop.db.model.Product;

public class ProductDaoTest extends AbstractTest {
	private static final IDao<Integer, Product> dao = ProductDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Product entity = new Product();
		entity.setTitle("title");
		entity.setPrice(80);
		entity.setDescription("description");
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Product entity = new Product();
		entity.setTitle("title");
		entity.setPrice(80);
		entity.setDescription("description");
		dao.insert(entity);

		String newTitle = "TITLE_NEW";
		entity.setTitle("title");
		entity.setPrice(80);
		entity.setDescription("description");
		dao.update(entity);
		
		String newDescription = "DESCRIPTION_NEW";
		entity.setTitle("title");
		entity.setPrice(80);
		entity.setDescription("description");
		dao.update(entity);

		Integer newPrice = 80;
		entity.setTitle("title");
		entity.setPrice(80);
		entity.setDescription("description");
		dao.update(entity);

		
		Product updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals(newTitle, updatedEntity.getTitle());
		Assertions.assertNotEquals(updatedEntity.getPrice(), updatedEntity.getDescription());
	}

	@Test
	public void testDelete() {
		Product entity = new Product();
		entity.setTitle("title");
		entity.setPrice(80);
		entity.setDescription("description");
		dao.insert(entity);

		dao.delete(entity.getId());

		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Product entity = new Product();
		entity.setTitle("title");
		entity.setPrice(80);
		entity.setDescription("description");
		dao.insert(entity);

		Product selectedEntity = dao.getById(entity.getId());

		Assertions.assertEquals(entity.getTitle(), selectedEntity.getTitle());
		Assertions.assertEquals(entity.getPrice(), selectedEntity.getPrice());
		Assertions.assertEquals(entity.getDescription(), selectedEntity.getDescription());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
        Product entity = new Product();
			entity.setTitle("TITLE" + i); // generate some random meaningless name as it is just a test (the data can be unreal)
			entity.setPrice(80);
			entity.setDescription("description");
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}
}