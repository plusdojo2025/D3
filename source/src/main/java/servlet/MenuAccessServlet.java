package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDAO;
import dao.VisitorDAO;
import dto.Customer;
import dto.Store;

@WebServlet("/MenuAccessServlet")
public class MenuAccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(60 * 60 * 2);

		int storeId = Integer.parseInt(request.getParameter("store"));
		Store store = new Store();
		store.setStore_id(storeId);
		session.setAttribute("store", store);

		Customer customer = new Customer();
		customer.setCustomer_id(storeId);
		customer.setCustomer_name("ゲスト");
		customer.setCustomer_email("guest@example.com");
		customer.setCustomer_password("guestPass");
		customer.setCustomer_birthday("2000-01-01");

		session.setAttribute("customer", customer);
		session.setAttribute("isVisitor", true);
		session.setAttribute("islogin", false);

		response.sendRedirect(request.getContextPath() + "/MenuListServlet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Store store = (Store) session.getAttribute("store");
		int storeId = store.getStore_id();

		String userType = request.getParameter("userType");

		if ("guest".equals(userType)) {
			session.setAttribute("isLogin", true);

			CustomerDAO customerDAO = new CustomerDAO();
			VisitorDAO visitorDAO = new VisitorDAO();

			Customer customer = (Customer) session.getAttribute("customer");
			int guestCount = customerDAO.countGuest();
			guestCount++;
			customer.setCustomer_email("guest" + guestCount + "@example.com");

			if (customerDAO.insert(customer)) {
				
				int customerId = customerDAO.getCustomerIdByCustomerEmail(customer.getCustomer_email());
				if (customerId != -1) {
					
					if (visitorDAO.insertVisitor(customerId, storeId)) {
						session.setAttribute("visitId", visitorDAO.getVisitorId(customerId, storeId));
					}
				}
			}
		} else if ("customer".equals(userType)) {
			session.setAttribute("isLogin", true);

			String email = (String) request.getParameter("email");
			String password = (String) request.getParameter("password");

			if (email != null && password != null) {
				Customer customer = new CustomerDAO().login(email, password);
				if (customer != null) {
					session.setAttribute("customer", customer);

					VisitorDAO visitorDAO = new VisitorDAO();
					int customerId = customer.getCustomer_id();

					if (visitorDAO.insertVisitor(customerId, storeId)) {
						session.setAttribute("visitId", visitorDAO.getVisitorId(customerId, storeId));
					}
				}
			}
		}
		response.sendRedirect(request.getContextPath() + "/MenuListServlet");
	}
}
