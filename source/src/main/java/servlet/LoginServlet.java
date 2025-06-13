package servlet;

	import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StoreDAO;
import dto.Store;
	public class LoginServlet {


	@WebServlet("/storeLogin")
	public class StoreLoginServlet extends HttpServlet {

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // フォームからの入力値取得
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");

	        String errorMsg = null;

	        // 入力チェック
	        if (email == null || email.isEmpty()) {
	            errorMsg = "メールアドレスを入力してください。";
	        } else if (password == null || password.isEmpty()) {
	            errorMsg = "パスワードを入力してください。";
	        }

	        if (errorMsg != null) {
	            request.setAttribute("errorMsg", errorMsg);
	            request.getRequestDispatcher("/storeLogin.jsp").forward(request, response);
	            return;
	        }

	        // DAOを使ってログイン認証
	        StoreDAO dao = new StoreDAO();
	        Store store = dao.login(email, password);

	        if (store != null) {
	            // ログイン成功時：セッションにstore情報を保存し店舗事務画面へリダイレクト
	            HttpSession session = request.getSession();
	            session.setAttribute("loginStore", store);
	            response.sendRedirect(request.getContextPath() + "/storeOffice.jsp");  // 店舗事務画面へ遷移
	        } else {
	            // ログイン失敗時
	            request.setAttribute("errorMsg", "メールアドレスかパスワードが違います。");
	            request.getRequestDispatcher("/storeLogin.jsp").forward(request, response);
	        }
	    }

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // GETはログイン画面表示へ
	        request.getRequestDispatcher("/storeLogin.jsp").forward(request, response);
	    }
	
	}
	
	}
	
