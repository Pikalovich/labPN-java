package by.grsu.npikalovich.shop.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.npikalovich.shop.db.dao.AbstractDao;
import by.grsu.npikalovich.shop.db.dao.IDao;
import by.grsu.npikalovich.shop.db.model.Client;

public class ClientDaoImpl extends AbstractDao implements IDao<Integer, Client> {

// single instance of this class to be used by the all consumers
	public static final ClientDaoImpl INSTANCE = new ClientDaoImpl();

// private constructor disallows instantiation of this class ('Singleton'
// pattern) outside of current class
	private ClientDaoImpl() {
		super();
	}

	@Override
	public void insert(Client entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("insert into client(first_name, last_name, email, time) values(?,?,?,?)");
			pstmt.setString(1, entity.getFirstName());
			pstmt.setString(2, entity.getLastName());
			pstmt.setString(3, entity.getEmail());
			pstmt.setInt(4, entity.getTime());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "client"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Client entity", e);
		}
	}

	@Override
	public void update(Client entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("update client set first_name=?, last_name=?, email=?, time=? where id=?");
			pstmt.setString(1, entity.getFirstName());
			pstmt.setString(2, entity.getLastName());
			pstmt.setString(3, entity.getEmail());
			pstmt.setInt(4, entity.getTime());
			pstmt.setInt(5, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update Client entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from client where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete Client entity", e);
		}

	}

	@Override
	public Client getById(Integer id) {
		Client entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from client where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Client entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Client> getAll() {
		List<Client> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from client");
			while (rs.next()) {
				Client entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Client entities", e);
		}

		return entitiesList;
	}

	private Client rowToEntity(ResultSet rs) throws SQLException {
		Client entity = new Client();
		entity.setId(rs.getInt("id"));
		entity.setFirstName(rs.getString("first_Name"));
		entity.setLastName(rs.getString("last_Name"));
		entity.setEmail(rs.getString("email"));
		entity.setTime(rs.getInt("time"));
		return entity;
	}

}
