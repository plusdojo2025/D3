package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDAO;
import dto.Cart;
import dto.Commodity;

@WebServlet("/OrderSubmitServlet")

@SuppressWarnings("unchecked")

public class OrderSubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		List<Cart> cartList = new ArrayList<Cart>();

		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();

			if (paramName.startsWith("cart_")) {
				String idStr = paramName.substring(5);
				int id = Integer.parseInt(idStr);
				int quantity = Integer.parseInt(request.getParameter(paramName));

				CartDAO dao = new CartDAO();
				Commodity commodity = dao.getCommodityById(id);

				// カートに追加
				cartList.add(new Cart(commodity, quantity));
			}
		}

		session.setAttribute("cart", cartList);

		if (cartList != null) {
			response.sendRedirect(request.getContextPath() + "/OrderListServlet");
		} else {
			// MenuListServletにリダイレクトする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/MenuListServlet");
			dispatcher.forward(request, response);
		}
	}
}
