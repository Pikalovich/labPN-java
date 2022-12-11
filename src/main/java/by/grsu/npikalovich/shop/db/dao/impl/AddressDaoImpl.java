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
import by.grsu.npikalovich.shop.db.model.Client;
import by.grsu.npikalovich.shop.web.dto.SortDto;
import by.grsu.npikalovich.shop.web.dto.TableStateDto;

public class AddressDaoImpl extends AbstractDao implements IDao<Integer, Address> {

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
			PreparedStatement pstmt = c
					.prepareStatement("insert into address (town, house, flat, street) values(?,?,?,?)");
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
			PreparedStatement pstmt = c
					.prepareStatement("update address set town=?, house=?, flat=?, street=? where id=? ");
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
		List<Address> entitiesList = new ArrayList<>();
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
	
	@Override
	public List<Address> find(TableStateDto tableStateDto) {
		List<Address> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			StringBuilder sql = new StringBuilder("select * from address");

			final SortDto sortDto = tableStateDto.getSort();
			if (sortDto != null) {
				sql.append(String.format(" order by %s %s", sortDto.getColumn(), resolveSortOrder(sortDto)));
			}

			sql.append(" limit " + tableStateDto.getItemsPerPage());
			sql.append(" offset " + resolveOffset(tableStateDto));

			System.out.println("searching Cars using SQL: " + sql);
			ResultSet rs = c.createStatement().executeQuery(sql.toString());
			while (rs.next()) {
				Address entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Car entities", e);
		}
		return entitiesList;
	}

	@Override
	public int count() {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select count(*) as c from address");
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt("c");
		} catch (SQLException e) {
			throw new RuntimeException("can't get cars count", e);
		}
	}


}