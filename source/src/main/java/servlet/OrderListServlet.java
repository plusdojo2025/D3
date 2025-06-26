package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrderListDAO;
import dao.VisitorDAO;
import dto.Cart;
import dto.Commodity;
import dto.Customer;
import dto.OrderList;

@WebServlet("/OrderListServlet")
@SuppressWarnings("unchecked")

public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		List<Cart> cartList = (List<Cart>) session.getAttribute("cart");
		if (cartList == null) {
			cartList = new ArrayList<>();
		}

		List<OrderList> orderList = new ArrayList<>();
		for (Cart cart : cartList) {
			OrderList order = new OrderList();
			order.setCommodity(cart.getCommodity());
			order.setOrder_quantity(cart.getQuantity());
			orderList.add(order);
		}

		request.setAttribute("orderList", orderList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/OrderList.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		Integer visitId = (Integer)session.getAttribute("visitId");
		if (visitId == null || visitId < 1) {
			session.setAttribute("isLogin", false);
			response.sendRedirect(request.getContextPath() + "/MenuAccessServlet");
		}

		VisitorDAO visitorDAO = new VisitorDAO();
		if (visitorDAO.isCurrentVisitByVisitId(visitId) == false) {
			response.sendRedirect(request.getContextPath() + "/MenuAccessServlet");
		}
		
		String[] commodityIds = request.getParameterValues("commodityId");
		String[] quantitys = request.getParameterValues("commodityQuantity");
		
		Customer customer = (Customer)session.getAttribute("customer");

		OrderListDAO orderListDAO = new OrderListDAO();
		List<OrderList> orderList = new ArrayList<OrderList>();
		for (int i = 0; i < commodityIds.length; i++) {
			OrderList order = new OrderList();

			int commodityId = Integer.parseInt(commodityIds[i]);
			Commodity commodity = orderListDAO.getCommodityById(commodityId);
			order.setCommodity(commodity);

			order.setCustomer(new Customer(customer.getCustomer_id(), "", "", "", ""));
			order.setOrder_quantity(Integer.parseInt(quantitys[i]));
			order.setVisit_id(visitId);
			orderList.add(order);
		}
		
		if (orderListDAO.insert(orderList) == false) {
			response.sendRedirect(request.getContextPath() + "/MenuAccessServlet");
		}

		response.sendRedirect(request.getContextPath() + "/MenuListServlet");
	}
}
