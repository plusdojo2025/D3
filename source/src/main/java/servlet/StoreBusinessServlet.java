package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VisitorDAO;
import dto.Visitor;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/StoreBusinessServlet")
public class StoreBusinessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 来店者表示	
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(timestamp);
        
        VisitorDAO dao = new VisitorDAO();
        List<Visitor> visitor = dao.getVisitorByDate(date);
        
        request.setAttribute("visitors", visitor);
        
        
        /*注文中商品 TODO
        OrderList  order = new OrderList();
        order.setOrder_datetime(date);
        OrderListDAO dao = new OrderListDAO();
        List<OrderList> orderList = new ArrayList<OrderList>();
        orderList = dao.select_new(order);
        
        int orderCount = orderList.size();
        HttpSession session = request.getSession();
        int oldOrderCount = 0;
        if (session.getAttribute("orderCount") != null) {
        	oldOrderCount = Integer.parseInt((String) session.getAttribute("orderCount"));
        }
        */
        
		/*
		//　現在日時取得
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String date = sdf.format(timestamp);
		OrderList order = new OrderList();
		order.setOrder_datetime(date);
		List<OrderList> orderData = dao.select_new(order);
		

		// request.setAttribute("連絡事項", data1);
		request.setAttribute("orderData", orderData);
		 */

		// 業務画面ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StoreBusiness.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 業務画面ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StoreBusiness.jsp");
		dispatcher.forward(request, response);

		// ○○ページにフォワードする
		// RequestDispatcher dispatcher =
		// request.getRequestDispatcher("/WEB-INF/jsp/○○.jsp");
		// dispatcher.forward(request, response);
	}
}
