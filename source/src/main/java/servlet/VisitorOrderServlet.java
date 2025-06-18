package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderListDAO;
import dao.VisitorDAO;
import dto.OrderList;
import dto.Visitor;

@WebServlet("/VisitorOrderServlet")
public class VisitorOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
        //注文中商品
		String date = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
		VisitorDAO visitorDAO = new VisitorDAO();
        OrderListDAO orderListDAO = new OrderListDAO();

        List<Visitor> visitors = visitorDAO.getVisitorByDate(date);
        for (Visitor v: visitors) {
        	List<OrderList> orders = orderListDAO.getTodayOrderByCustomerId(v.getCustomer().getCustomer_id(), date);
        	v.setOrders(orders);
        }
		
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.print("[");
        visitors = visitorDAO.getVisitorByDate(date);
        List<OrderList> orders = new ArrayList<OrderList>();
        for (Visitor v : visitors) {
        	orders = orderListDAO.getTodayOrderByCustomerId(v.getCustomer().getCustomer_id(), date);
        }
        	for (int j = 0; j < orders.size(); j++) {
        		OrderList o = orders.get(j);
        		out.print("{");
        		out.printf("\"customerName\":\"%s\",", o.getCustomer().getCustomer_name());
        		out.printf("\"commodityName\":\"%s\",", o.getCommodity().getCommodity_name());
        		out.printf("\"quantity\":%d,", o.getOrder_quantity());
        		out.printf("\"orderId\":%d", o.getOrder_id());
        		out.print("}");
        		if (j < orders.size() - 1)
        			out.print(",");
        	}
        out.print("]");
	}
}
