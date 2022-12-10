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
import by.grsu.npikalovich.shop.db.dao.impl.OrderDaoImpl;
import by.grsu.npikalovich.shop.db.dao.impl.ClientDaoImpl;
import by.grsu.npikalovich.shop.db.dao.impl.ProductDaoImpl;
import by.grsu.npikalovich.shop.db.dao.impl.AddressDaoImpl;
import by.grsu.npikalovich.shop.db.model.Order;
import by.grsu.npikalovich.shop.db.model.Client;
import by.grsu.npikalovich.shop.db.model.Address;
import by.grsu.npikalovich.shop.db.model.Product;
import by.grsu.npikalovich.shop.web.dto.ClientDto;
import by.grsu.npikalovich.shop.web.dto.ProductDto;
import by.grsu.npikalovich.shop.web.dto.OrderDto;
import by.grsu.npikalovich.shop.web.ValidationUtils;
import by.grsu.npikalovich.shop.web.dto.AddressDto;

public class OrderServlet extends HttpServlet {
	private static final IDao<Integer, Order> orderDao = OrderDaoImpl.INSTANCE;
	private static final IDao<Integer, Client> clientDao = ClientDaoImpl.INSTANCE;
	private static final IDao<Integer, Product> productDao = ProductDaoImpl.INSTANCE;
	private static final IDao<Integer, Address> addressDao = AddressDaoImpl.INSTANCE;

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
		List<Order> orders = orderDao.getAll(); // get data

		List<OrderDto> dtos = orders.stream().map((entity) -> {
			OrderDto dto = new OrderDto();
			// copy necessary fields as-is
			dto.setId(entity.getId());
			dto.setClientId(entity.getClientId());
			dto.setDeliveryAddressId(entity.getDeliveryAddressId());
			dto.setProductId(entity.getProductId());
			dto.setCount(entity.getCount());
			dto.setPrice(entity.getPrice());

			// build data for complex fields
			Client client = clientDao.getById(entity.getClientId());
			dto.setClientName(client.getLastName() + " " + client.getFirstName());

			Address address = addressDao.getById(entity.getDeliveryAddressId());
			dto.setDeliveryAddressName(address.getTown() + " " + address.getStreet() + " " + address.getHouse());

			Product product = productDao.getById(entity.getProductId());
			dto.setProductName(product.getTitle() + " " + product.getPrice());
			return dto;

		}).collect(Collectors.toList());

		req.setAttribute("list", dtos); // set data as request attribute (like "add to map") to be used later in JSP
		req.getRequestDispatcher("order.jsp").forward(req, res); // delegate request processing to JSP
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String orderIdStr = req.getParameter("id");

		OrderDto dto = new OrderDto();
		if (!Strings.isNullOrEmpty(orderIdStr)) {
			// object edit
			Integer orderId = Integer.parseInt(orderIdStr);
			Order entity = orderDao.getById(orderId);
			dto.setId(entity.getId());
			dto.setClientId(entity.getClientId());
			dto.setDeliveryAddressId(entity.getDeliveryAddressId());
			dto.setProductId(entity.getProductId());
			dto.setCount(entity.getCount());
			dto.setPrice(entity.getPrice());
		}
		req.setAttribute("dto", dto);
		req.setAttribute("allProducts", getAllProductsDtos());
		req.setAttribute("allClients", getAllClientsDtos());
		req.setAttribute("allAddresses", getAllAddressesDtos());
		req.getRequestDispatcher("order-edit.jsp").forward(req, res);
	}

	private List<ProductDto> getAllProductsDtos() {
		return productDao.getAll().stream().map((entity) -> {
			ProductDto dto = new ProductDto();
			dto.setId(entity.getId());
			dto.setName(entity.getTitle());
			return dto;
		}).collect(Collectors.toList());
	}

	private List<ClientDto> getAllClientsDtos() {
		return clientDao.getAll().stream().map((entity) -> {
			ClientDto dto = new ClientDto();
			dto.setId(entity.getId());
			dto.setName(entity.getLastName());
			dto.setName(entity.getFirstName());
			return dto;
		}).collect(Collectors.toList());
	}

	private List<AddressDto> getAllAddressesDtos() {
		return addressDao.getAll().stream().map((entity) -> {
			AddressDto dto = new AddressDto();
			dto.setId(entity.getId());
			dto.setName(entity.getTown());
			dto.setName(entity.getStreet());
			dto.setHouse(entity.getHouse());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Order order = new Order();
		String orderIdStr = req.getParameter("id");
		String productIdStr = req.getParameter("productId");
		String clientIdStr = req.getParameter("clientId");
		String addressIdStr = req.getParameter("deliveryAddressId");

		order.setCount(Integer.parseInt(req.getParameter("count")));
		order.setClientId(clientIdStr == null ? null : Integer.parseInt(clientIdStr));
		order.setProductId(productIdStr == null ? null : Integer.parseInt(productIdStr));
		order.setDeliveryAddressId(addressIdStr == null ? null : Integer.parseInt(addressIdStr));
		order.setPrice(Integer.parseInt(req.getParameter("price")));

		if (Strings.isNullOrEmpty(orderIdStr)) {
			// new entity
			// order.setCreated();
			orderDao.insert(order);
		} else {
			// updated entity
			order.setId(Integer.parseInt(orderIdStr));
			orderDao.update(order);
		}
		res.sendRedirect("/order"); // will send 302 back to client and client will execute GET /car
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");

		String id = req.getParameter("id");
		if (!ValidationUtils.isInteger(id)) {
			res.sendError(400);
			return;
		}

		orderDao.delete(Integer.parseInt(id));
	}
}