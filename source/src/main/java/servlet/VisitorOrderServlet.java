package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrderListDAO;
import dao.VisitorDAO;
import dto.OrderList;
import dto.Store;
import dto.Visitor;

@WebServlet("/VisitorOrderServlet")
public class VisitorOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Store store = (Store)session.getAttribute("store");
		if (store == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}
		
		LocalDateTime baseTime;
		if (LocalTime.now().isBefore(LocalTime.NOON)) {
		    baseTime = LocalDate.now().minusDays(1).atTime(12, 0);
		} else {
		    baseTime = LocalDate.now().atTime(12, 0);
		}
		
		java.util.Date dateAtNoon = Date.from(baseTime.atZone(ZoneId.systemDefault()).toInstant());
		String formatted = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(dateAtNoon);
		
		
		VisitorDAO visitorDAO = new VisitorDAO();
		OrderListDAO orderListDAO = new OrderListDAO();

		List<Visitor> visitors = visitorDAO.getCurrentVisitorByStoreId(store.getStore_id());
		List<OrderList> orders = new ArrayList<>();

		for (Visitor v : visitors) {
			List<OrderList> visitorOrders = orderListDAO.getTodayOrderByCustomerId(v.getCustomer().getCustomer_id(), formatted);
			orders.addAll(visitorOrders);
		}

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		List<String> jsonList = new ArrayList<>();
		for (OrderList o : orders) {
			String json = String.format(
				"{\"customerName\":\"%s\",\"commodityName\":\"%s\",\"quantity\":%d,\"orderId\":%d}",
				o.getCustomer().getCustomer_name(),
				o.getCommodity().getCommodity_name(),
				o.getOrder_quantity(),
				o.getOrder_id()
			);
			jsonList.add(json);
		}

		out.print("[" + String.join(",", jsonList) + "]");
	}
}