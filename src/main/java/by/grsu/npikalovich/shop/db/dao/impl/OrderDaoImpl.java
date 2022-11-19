package by.grsu.npikalovich.shop.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.npikalovich.shop.db.dao.AbstractDao;
import by.grsu.npikalovich.shop.db.dao.IDao;
import by.grsu.npikalovich.shop.db.model.Order;

public class OrderDaoImpl extends AbstractDao implements IDao<Integer, Order> {
	public static final OrderDaoImpl INSTANCE = new OrderDaoImpl();

	private OrderDaoImpl() {
		super();
	}

	@Override
	public void insert(Order entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("insert into order_object (cnt, product_id, client_id, delivery_address_id, price) values(?,?,?,?,?)");
			pstmt.setInt(1, entity.getCount());
			pstmt.setInt(2, entity.getProductId());
			// owner_id has type Integer, but if it can be null we have to use setObject()
			// instead of setInt()
			pstmt.setInt(3, entity.getClientId());
			pstmt.setString(4, entity.getDeliveryAdressId());
			pstmt.setInt(5, entity.getPrice());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "order"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Order entity", e);
		}

	}

	@Override
	public void update(Order entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("update order_object set cnt=?, product_id=?, client_id=?, delivery_address_id=?,  updatedprice=? where id=?");
			pstmt.setInt(1, entity.getCount());
			pstmt.setInt(2, entity.getProductId());
			pstmt.setInt(3, entity.getClientId());
			pstmt.setString(4, entity.getDeliveryAdressId());
			pstmt.setInt(5, entity.getPrice());
			pstmt.setInt(6, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update Order entity", e);
		}

	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from order where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete Order entity", e);
		}
	}

	@Override
	public Order getById(Integer id) {
		Order entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from order where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Order entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Order> getAll() {
		List<Order> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from order");
			while (rs.next()) {
				Order entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Order entities", e);
		}

		return entitiesList;
	}

	private Order rowToEntity(ResultSet rs) throws SQLException {
		Order entity = new Order();
		entity.setId(rs.getInt("id"));
		entity.setCount(rs.getInt("cnt"));
		entity.setProductId(rs.getInt("product_id"));
		// getObject() is unsupported by current JDBC driver. We will get "0" if field is NULL in DB
		entity.setClientId(rs.getInt("client_id"));
		entity.setDeliveryAdressId(rs.getString("dilivery_adress_id"));
		entity.setPrice(rs.getInt("price"));
		return entity;
	}
}

Pikalovich