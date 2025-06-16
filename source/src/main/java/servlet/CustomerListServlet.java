package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDAO;
import dto.Customer;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/CustomerListServlet")
public class CustomerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * // もしもログインしていなかったらログインサーブレットにリダイレクトする HttpSession session =
		 * request.getSession(); if (session.getAttribute("id") == null) {
		 * response.sendRedirect("/D3/LoginServlet"); return; }
		 */

		// 検索ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StoreStaff.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/D3/LoginServlet");
			return;
		}*/

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String customer_name = request.getParameter("customer_name");

		// 検索処理を行う
		CustomerDAO cDao = new CustomerDAO();
		List<Customer> customerList = cDao.select(new Customer(0, customer_name, "", "", ""));

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("customerList", customerList);

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/CustomerList.jsp");
		dispatcher.forward(request, response);
	}
}
