package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDAO;
import dto.Customer;
import dto.Result;

/**
 * Servlet implementation class UpdateDeleteCustomerServlet
 */
@WebServlet("/UpdateDeleteCustomerServlet")
public class UpdateDeleteCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/D3/LoginServlet");
			return;
		}

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		int customer_id = Integer.parseInt(request.getParameter("customer_id"));
		String customer_name = request.getParameter("customer_name");
		String customer_email = request.getParameter("customer_email");
		String customer_password = request.getParameter("customer_password");
		String customer_birthday = request.getParameter("customer_birthday");

		// 更新または削除を行う
		CustomerDAO cDao = new CustomerDAO();
		if (request.getParameter("submit").equals("更新")) {
			if (cDao.update(new Customer(customer_id, customer_name, customer_email, customer_password, customer_birthday))) { // 更新成功
				request.setAttribute("result", new Result("顧客情報を更新しました。", "/D3/CustomerListServlet"));
			} else { // 更新失敗
				request.setAttribute("result", new Result("顧客情報を更新できませんでした。", "/D3/CustomerListServlet"));
			}
		} else {
			if (cDao.delete(new Customer(customer_id, customer_name, customer_email, customer_password, customer_birthday))) { // 削除成功
				request.setAttribute("result", new Result("顧客情報を削除しました。", "/D3/CustomerListServlet"));
			} else { // 削除失敗
				request.setAttribute("result", new Result("顧客情報を削除できませんでした。", "/D3/CustomerListServlet"));
			}
		}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Result.jsp");
		dispatcher.forward(request, response);
	}
}
