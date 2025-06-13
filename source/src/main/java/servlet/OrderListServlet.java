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

import dao.OrderListDAO;
import dto.OrderList;

@WebServlet("/OrderListServlet")
public class OrderListServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*
		HttpSession session = request.getSession();
		// MenuServletから渡されるデータ型によってList内の型変更
		List<OrderList> sessionList = (List<OrderList>)session.getAttribute("orderList");
		
		
		List<OrderList> orderList = new ArrayList<OrderList>();
		for (OrderList order : sessionList) {
			int commodityId;
			int quantity;
		}
		request.setAttribute("orderList", orderList);
		*/
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
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/MenuServlet");
		dispatcher.forward(request, response);
	}
}
