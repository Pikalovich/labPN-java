package by.grsu.npikalovich.shop.db.dao.iml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.npikalovich.shop.db.dao.IDao;
import by.grsu.npikalovich.shop.db.dao.impl.AddressDaoImpl;
import by.grsu.npikalovich.shop.db.model.Address;

public class AddressDaoTest extends AbstractTest {
private static final IDao<Integer, Address> dao = AddressDaoImpl.INSTANCE;

@Test
public void testInsert() {
Address entity = new Address();
entity.setTown("Grodno");
entity.setHouse(5);
entity.setFlat(13);
entity.setStreet("Gaspadarchaya");
dao.insert(entity);
Assertions.assertNotNull(entity.getId());
}

@Test
public void testUpdate() {
Address entity = new Address();
entity.setTown("Grodno");
entity.setHouse(5);
entity.setFlat(13);
entity.setStreet("Gaspadarchaya");
dao.insert(entity);

String newTown = "Grodno";
String newStreet = "Gaspadarchaya";
dao.update(entity);



Address updatedEntity = dao.getById(entity.getId());
Assertions.assertEquals(newTown, updatedEntity.getTown());
Assertions.assertEquals(newStreet, updatedEntity.getStreet());
}

@Test
public void testDelete() {
Address entity = new Address();
entity.setTown("Grodno");
entity.setHouse(5);
entity.setFlat(13);
entity.setStreet("Gaspadarchaya");
dao.insert(entity);

dao.delete(entity.getId());

Assertions.assertNull(dao.getById(entity.getId()));
}

@Test
public void testGetById() {
Address entity = new Address();
entity.setTown("Grodno");
entity.setHouse(5);
entity.setFlat(13);
entity.setStreet("Gaspadarchaya");
dao.insert(entity);

Address selectedEntity = dao.getById(entity.getId());

Assertions.assertEquals(entity.getTown(), selectedEntity.getTown());
Assertions.assertEquals(entity.getHouse(), selectedEntity.getHouse());
Assertions.assertEquals(entity.getFlat(), selectedEntity.getFlat());
Assertions.assertEquals(entity.getStreet(), selectedEntity.getStreet());
}

@Test
public void testGetAll() {
int expectedCount = getRandomNumber(1, 5);
for (int i = 1; i <= expectedCount; i = i + 1) {
Address entity = new Address();
entity.setTown("TOWN" + i); // generate some random meaningless name as it is just a test (the data can be unreal)
entity.setHouse(5);
entity.setFlat(13);
entity.setStreet("Gaspadarchaya");
dao.insert(entity);
}

Assertions.assertEquals(expectedCount, dao.getAll().size());
}
}
