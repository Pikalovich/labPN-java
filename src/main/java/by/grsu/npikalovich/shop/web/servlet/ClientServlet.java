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
import by.grsu.npikalovich.shop.db.dao.impl.ClientDaoImpl;
import by.grsu.npikalovich.shop.db.model.Client;
import by.grsu.npikalovich.shop.web.ValidationUtils;
import by.grsu.npikalovich.shop.web.dto.ClientDto;

public class ClientServlet extends HttpServlet {
	private static final IDao<Integer, Client> clientDao = ClientDaoImpl.INSTANCE;

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
		List<Client> orders = clientDao.getAll(); // get data
		List<ClientDto> dtos = orders.stream().map((entity) -> {
			ClientDto dto = new ClientDto();
			// copy necessary fields as-is
			dto.setId(entity.getId());
			dto.setLastName(entity.getLastName());
			dto.setFirstName(entity.getFirstName());
			dto.setEmail(entity.getEmail());
			dto.setTime(entity.getTime());
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos); // set data as request attribute (like "add to map") to be used later in JSP
		req.getRequestDispatcher("client.jsp").forward(req, res); // delegate request processing to JSP
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String clientIdStr = req.getParameter("id");
		ClientDto dto = new ClientDto();
		if (!Strings.isNullOrEmpty(clientIdStr)) {
			// object edit
			Integer clientId = Integer.parseInt(clientIdStr);
			Client entity = clientDao.getById(clientId);
			dto.setId(entity.getId());
			dto.setLastName(entity.getLastName());
			dto.setFirstName(entity.getFirstName());
			dto.setEmail(entity.getEmail());
			dto.setTime(entity.getTime());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("client-edit.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Client client = new Client();
		String clientIdStr = req.getParameter("id");

		client.setLastName(req.getParameter("lastName"));
		client.setFirstName(req.getParameter("firstName"));
		client.setEmail(req.getParameter("email"));
		client.setTime(Integer.parseInt(req.getParameter("time")));

		if (Strings.isNullOrEmpty(clientIdStr)) {
			// new entity

			clientDao.insert(client);
		} else {
			// updated entity
			client.setId(Integer.parseInt(clientIdStr));
			clientDao.update(client);
		}
		res.sendRedirect("/client"); // will send 302 back to client and client will execute GET /car
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		String id = req.getParameter("id");
		if (!ValidationUtils.isInteger(id)) {
			res.sendError(400);
			return;
		}

		clientDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}