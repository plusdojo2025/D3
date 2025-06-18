package servlet;

import java.io.IOException;
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
		HttpSession session = request.getSession();
		
		List<Cart> cart = (List<Cart>) session.getAttribute("cart");
		
		if (cart != null) {
			response.sendRedirect(request.getContextPath() + "/OrderListServlet");
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/OrderListServlet");
			//dispatcher.forward(request, response);

			
		} else {
			// MenuListServletにリダイレクトする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/MenuListServlet");
			dispatcher.forward(request, response);
		}
	}
}
