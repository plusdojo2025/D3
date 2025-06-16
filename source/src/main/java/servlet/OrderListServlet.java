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

import dao.CartDAO;
import dao.OrderListDAO;
import dto.Cart;
import dto.OrderList;

@WebServlet("/OrderListServlet")
@SuppressWarnings("unchecked")

public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		List<Cart> cartList = new ArrayList<Cart>();
		if (session.getAttribute("orderList") != null) {
			cartList = (List<Cart>) session.getAttribute("orderList");
		}

		CartDAO cartDAO = new CartDAO();
		List<Cart> orderList = new ArrayList<Cart>();

		for (Cart cart : cartList) {
			int commodityId = cart.getCommodity().getCommodity_id();
			cart.setCommodity(cartDAO.getCommodityById(commodityId));
			orderList.add(cart);
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

		List<OrderList> orderListArray = new ArrayList<OrderList>();
		for (int i = 0; i < commodityIds.length; i++) {
			OrderList orderList = new OrderList();
			orderList.setCommodity_id(Integer.parseInt(commodityIds[i]));
			orderList.setOrder_quantity(Integer.parseInt(quantitys[i]));
			orderListArray.add(orderList);
		}

		OrderListDAO orderListDAO = new OrderListDAO();
		orderListDAO.insert(orderListArray);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/MenuServlet");
		dispatcher.forward(request, response);
	}
}
