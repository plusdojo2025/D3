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

import dto.Cart;

@WebServlet("/OrderSubmitServlet")
@SuppressWarnings("unchecked")

public class OrderSubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");

		List<Cart> orderList = new ArrayList<Cart>();
		if (request.getAttribute("orderList") != null) {
			orderList = (List<Cart>) request.getAttribute("orderList");

			HttpSession session = request.getSession();
			session.setAttribute("orderList", orderList);
			response.sendRedirect("/OrderListServlet");
		} else {
			// MenuListServletにリダイレクトする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/MenuListServlet");
			dispatcher.forward(request, response);
		}
	}
}
