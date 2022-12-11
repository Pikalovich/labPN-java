package by.grsu.npikalovich.shop.web.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Strings;

import by.grsu.npikalovich.shop.db.dao.IDao;
import by.grsu.npikalovich.shop.db.dao.impl.ProductDaoImpl;
import by.grsu.npikalovich.shop.db.model.Address;
import by.grsu.npikalovich.shop.db.model.Product;
import by.grsu.npikalovich.shop.web.dto.ProductDto;
import by.grsu.npikalovich.shop.web.dto.TableStateDto;
import by.grsu.npikalovich.shop.web.ValidationUtils;

public class ProductServlet extends AbstractListServlet {
	private static final IDao<Integer, Product> productDao = ProductDaoImpl.INSTANCE;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doGet");
		String viewParam = req.getParameter("view");
		if ("edit".equals(viewParam)) {
			handleEditView(req, res);
		} else {
			handleListView(req, res);
		}
	}

	private void handleListView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int totalProducts = productDao.count(); // get count of ALL items
		final TableStateDto tableStateDto = resolveTableStateDto(req, totalProducts);
		List<Product> orders = productDao.find(tableStateDto); // get data
		List<ProductDto> dtos = orders.stream().map((entity) -> {
			ProductDto dto = new ProductDto();
			// copy necessary fields as-is
			dto.setId(entity.getId());
			dto.setTitle(entity.getTitle());
			dto.setPrice(entity.getPrice());
			dto.setDescription(entity.getDescription());
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos); // set data as request attribute (like "add to map") to be used later in JSP
		req.getRequestDispatcher("catalog.jsp").forward(req, res); // delegate request processing to JSP
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String productIdStr = req.getParameter("id");
		ProductDto dto = new ProductDto();
		if (!Strings.isNullOrEmpty(productIdStr)) {
			// object edit
			Integer productId = Integer.parseInt(productIdStr);
			Product entity = productDao.getById(productId);
			dto.setId(entity.getId());
			dto.setTitle(entity.getTitle());
			dto.setPrice(entity.getPrice());
			dto.setDescription(entity.getDescription());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("catalog-edit.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Product product = new Product();
		String productIdStr = req.getParameter("id");

		product.setTitle(req.getParameter("title"));
		product.setDescription(req.getParameter("description"));
		product.setPrice(Integer.parseInt(req.getParameter("price")));

		if (Strings.isNullOrEmpty(productIdStr)) {
			// new entity

			productDao.insert(product);
		} else {
			// updated entity
			product.setId(Integer.parseInt(productIdStr));
			productDao.update(product);
		}
		res.sendRedirect("/product"); // will send 302 back to client and client will execute GET /car
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		String id = req.getParameter("id");
		if (!ValidationUtils.isInteger(id)) {
			res.sendError(400);
			return;
		}

		productDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}