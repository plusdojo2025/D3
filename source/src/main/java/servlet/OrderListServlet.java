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

		String[] commodityIds = request.getParameterValues("commodityId");
		String[] quantitys = request.getParameterValues("commodityQuantity");
		
		HttpSession session = request.getSession();
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
			orderList.add(order);
		}
		orderListDAO.insert(orderList);

		response.sendRedirect(request.getContextPath() + "/MenuListServlet");
	}
}
