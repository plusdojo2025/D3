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
import dao.StoreDAO;
import dto.Customer;
import dto.Store;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String userType = request.getParameter("userType");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		// ユーザーチェック
		if (userType == null) {
			request.setAttribute("errorMsg", "ユーザー種別が不明です。。");
			request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp").forward(request, response);
			return;
		}

		HttpSession session = request.getSession();
		
		if (session.getAttribute("isVisitor") == null)
			session.setAttribute("isVisitor", false);
		
		

		// ゲストログイン時
		if ("guest".equals(userType)) {
			Customer customer = new Customer();
			customer.setCustomer_id(0);
			customer.setCustomer_name("ゲスト");

			session.setAttribute("customer", customer);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/CustomerHomeServlet");
			dispatcher.forward(request, response);
		}

		
		
		// 入力チェック
		if ("store".equals(userType) || "customer".equals(userType)) {
			if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
				request.setAttribute("errorMsg", "全ての項目を入力してください。1");
				request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp").forward(request, response);
				return;
			}
		}

		// 店舗ログイン
		if ("store".equals(userType)) {
			Store store = new StoreDAO().login(email, password);
			if (store != null) {
				session.setAttribute("store", store);
				response.sendRedirect(request.getContextPath() + "/StoreBusinessServlet");
			} else {
				request.setAttribute("errorMsg", "店舗ログイン失敗。メールアドレスまたはパスワードが間違っています。");
				request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp").forward(request, response);
			}

			// 顧客ログイン
		} else if ("customer".equals(userType)) {
			Customer customer = new CustomerDAO().login(email, password);
			if (customer != null) {
				session.setAttribute("customer", customer);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/CustomerHomeServlet");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("errorMsg", "顧客ログイン失敗。メールアドレスまたはパスワードが間違っています。");
				request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp").forward(request, response);
			}

			// 新規登録
		} else if ("register".equals(userType)) {
			String name = request.getParameter("name");
			String regEmail = request.getParameter("email");
			String regPassword = request.getParameter("password");
			String birthday = request.getParameter("birthday");

			if (name == null || regEmail == null || regPassword == null || birthday == null || name.isEmpty()
					|| regEmail.isEmpty() || regPassword.isEmpty() || birthday.isEmpty()) {
				request.setAttribute("errorMsg", "すべての登録情報を入力してください。2");
				request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp").forward(request, response);
				return;
			}

			Customer newCustomer = new Customer();
			newCustomer.setCustomer_name(name);
			newCustomer.setCustomer_email(regEmail);
			newCustomer.setCustomer_password(regPassword);
			newCustomer.setCustomer_birthday(birthday);

			boolean result = new CustomerDAO().insert(newCustomer);
			if (result) {
				session.setAttribute("customer", newCustomer);
				// response.sendRedirect("/WEB-INF/jsp/CustomerHome.jsp");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/CustomerHomeServlet");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("errorMsg", "顧客ログイン失敗。");
				request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("errorMsg", "不正なユーザー種別です。");
			request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp").forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp");
		dispatcher.forward(request, response);
	}
}
