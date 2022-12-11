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
import by.grsu.npikalovich.shop.db.model.Product;
import by.grsu.npikalovich.shop.web.dto.SortDto;
import by.grsu.npikalovich.shop.web.dto.TableStateDto;

public class ProductDaoImpl extends AbstractDao implements IDao<Integer, Product> {

	// single instance of this class to be used by the all consumers
	public static final ProductDaoImpl INSTANCE = new ProductDaoImpl();

	// private constructor disallows instantiation of this class ('Singleton'
	// pattern) outside of current class
	private ProductDaoImpl() {
		super();
	}

	@Override
	public void insert(Product entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("insert into product(title, price, description) values(?,?,?)");
			pstmt.setString(1, entity.getTitle());
			pstmt.setInt(2, entity.getPrice());
			pstmt.setString(3, entity.getDescription());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "product"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Product entity", e);
		}
	}

	@Override
	public void update(Product entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("update product set title=?, price=?, description=? where id=?");
			pstmt.setString(1, entity.getTitle());
			pstmt.setInt(2, entity.getPrice());
			pstmt.setString(3, entity.getDescription());
			pstmt.setInt(4, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update Product entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from product where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete Product entity", e);
		}

	}

	@Override
	public Product getById(Integer id) {
		Product entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from Product where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Product entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Product> getAll() {
		List<Product> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from product");
			while (rs.next()) {
				Product entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Product entities", e);
		}

		return entitiesList;
	}

	private Product rowToEntity(ResultSet rs) throws SQLException {
		Product entity = new Product();
		entity.setId(rs.getInt("id"));
		entity.setTitle(rs.getString("title"));
		entity.setPrice(rs.getInt("price"));
		entity.setDescription(rs.getString("description"));
		return entity;
	}

	@Override
	public List<Product> find(TableStateDto tableStateDto) {
		List<Product> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			StringBuilder sql = new StringBuilder("select * from product");

			final SortDto sortDto = tableStateDto.getSort();
			if (sortDto != null) {
				sql.append(String.format(" order by %s %s", sortDto.getColumn(), resolveSortOrder(sortDto)));
			}

			sql.append(" limit " + tableStateDto.getItemsPerPage());
			sql.append(" offset " + resolveOffset(tableStateDto));

			System.out.println("searching Cars using SQL: " + sql);
			ResultSet rs = c.createStatement().executeQuery(sql.toString());
			while (rs.next()) {
				Product entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Product entities", e);
		}
		return entitiesList;
	}

	@Override
	public int count() {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select count(*) as c from product");
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt("c");
		} catch (SQLException e) {
			throw new RuntimeException("can't get cars count", e);
		}
	}

}
