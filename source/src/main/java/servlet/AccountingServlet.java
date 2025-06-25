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
import dto.OrderList;
import dto.Store;

@WebServlet("/AccountingServlet")
public class AccountingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int visitId = Integer.parseInt(request.getParameter("visitId"));

		OrderListDAO orderListDAO = new OrderListDAO();
		List<OrderList> orderList = orderListDAO.getOrderByVisitId(visitId);
		if (orderList == null)
			orderList = new ArrayList<OrderList>();

		int total = 0;
		for (OrderList order : orderList) {
			total += (order.getCommodity().getCommodity_price() * order.getOrder_quantity());
		}

		request.setAttribute("orderList", orderList);
		request.setAttribute("visitId", visitId);
		request.setAttribute("total", total);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Accounting.jsp");
		dispatcher.forward(request, response);
	}

	// POSTリクエストの処理（フォーム送信された場合など）
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// セッションからログイン中の店舗情報を取得
		HttpSession session = request.getSession();
		Store loginStore = (Store) session.getAttribute("store");

		// ログインしていない場合はログイン画面へリダイレクト
		if (loginStore == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}

		request.setCharacterEncoding("UTF-8");

		// 退店登録
		int visitId = Integer.parseInt(request.getParameter("visitId"));
		VisitorDAO visitorDAO = new VisitorDAO();
		visitorDAO.updateVisitorExit(visitId);

		response.sendRedirect(request.getContextPath() + "/StoreBusinessServlet");
	}
}
