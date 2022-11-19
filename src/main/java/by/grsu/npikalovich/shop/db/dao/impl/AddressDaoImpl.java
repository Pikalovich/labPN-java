package by.grsu.npikalovich.shop.db.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.npikalovich.shop.db.dao.AbstractDao;
import by.grsu.npikalovich.shop.db.dao.IDao;
import by.grsu.npikalovich.shop.db.model.Address;

public class AddressDaoImpl extends AbstractDao implements IDao<Integer, Address > {

	// single instance of this class to be used by the all consumers
	public static final AddressDaoImpl INSTANCE = new AddressDaoImpl();

	// private constructor disallows instantiation of this class ('Singleton'
	// pattern) outside of current class
	private AddressDaoImpl() {
		super();
	}

	@Override
	public void insert(Address entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into address (town, house, flat, street) values(?,?,?)");
			pstmt.setString(1, entity.getTown());
			pstmt.setInt(2, entity.getHouse());
			pstmt.setInt(3, entity.getFlat());
			pstmt.setString(4, entity.getStreet());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "address"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Address entity", e);
		}
	}

	@Override
	public void update(Address entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update client set town=?, house=?, flat=?, street=? where id=?");
			pstmt.setString(1, entity.getTown());
			pstmt.setInt(2, entity.getHouse());
			pstmt.setInt(3, entity.getFlat());
			pstmt.setString(4, entity.getStreet());
			pstmt.setInt(5, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update Address entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from address where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete Address entity", e);
		}

	}

	@Override
	public Address getById(Integer id) {
		Address entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from address where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Adress entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Address> getAll() {
		List<Address>entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from address");
			while (rs.next()) {
				Address entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select address entities", e);
		}

		return entitiesList;
	}

	private Address rowToEntity(ResultSet rs) throws SQLException {
		Address entity = new Address();
		entity.setId(rs.getInt("id"));
		entity.setTown(rs.getString("town"));
		entity.setHouse(rs.getInt("house"));
		entity.setFlat(rs.getInt("flat"));
		entity.setStreet(rs.getString("street"));
		return entity;
	}

}