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
import by.grsu.npikalovich.shop.db.dao.impl.AddressDaoImpl;
import by.grsu.npikalovich.shop.db.model.Address;
import by.grsu.npikalovich.shop.web.ValidationUtils;
import by.grsu.npikalovich.shop.web.dto.AddressDto;

public class AddressServlet extends HttpServlet {
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
		List<Address> orders = addressDao.getAll(); // get data
		List<AddressDto> dtos = orders.stream().map((entity) -> {
			AddressDto dto = new AddressDto();
			dto.setId(entity.getId());
			dto.setTown(entity.getTown());
			dto.setHouse(entity.getHouse());
			dto.setFlat(entity.getFlat());
			dto.setStreet(entity.getStreet());
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos); // set data as request attribute (like "add to map") to be used later in JSP
		req.getRequestDispatcher("address.jsp").forward(req, res); // delegate request processing to JSP
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String addressIdStr = req.getParameter("id");
		AddressDto dto = new AddressDto();
		if (!Strings.isNullOrEmpty(addressIdStr)) {
			// object edit
			Integer addressId = Integer.parseInt(addressIdStr);
			Address entity = addressDao.getById(addressId);
			dto.setId(entity.getId());
			dto.setTown(entity.getTown());
			dto.setHouse(entity.getHouse());
			dto.setFlat(entity.getFlat());
			dto.setStreet(entity.getStreet());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("address-edit.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Address address = new Address();
		String addressIdStr = req.getParameter("id");

		address.setTown(req.getParameter("town"));
		address.setStreet(req.getParameter("street"));
		address.setFlat(Integer.parseInt(req.getParameter("flat")));
		address.setHouse(Integer.parseInt(req.getParameter("house")));

		if (Strings.isNullOrEmpty(addressIdStr)) {
			// new entity

			addressDao.insert(address);
		} else {
			// updated entity
			address.setId(Integer.parseInt(addressIdStr));
			addressDao.update(address);
		}
		res.sendRedirect("/address"); // will send 302 back to client and client will execute GET /car
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		String id = req.getParameter("id");
		if (!ValidationUtils.isInteger(id)) {
			res.sendError(400);
			return;
		}
		addressDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}