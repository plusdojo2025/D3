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

import dao.KeepBottleDAO;
import dao.TalkDAO;
import dto.Customer;
import dto.KeepBottle;
import dto.Talk;

@WebServlet("/CustomerHomeServlet")
public class CustomerHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CustomerHomeServlet() {
		super();
	}

	/**
	 * 顧客ホーム画面表示処理（GET）
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// セッションからログイン中の顧客情報を取得
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");

		// ログインしていない場合はログイン画面へリダイレクト
		if (loginCustomer == null) {
			response.sendRedirect("/D3/LoginServlet");
			return;
		}

		// ニックネーム表示用にセット
		String nickname = loginCustomer.getNickname();
		request.setAttribute("nickname", nickname);

		// お知らせ取得（最新3件）
		List<Talk> talkList = TalkDAO.selectLatest(3);
		request.setAttribute("talkList", talkList);

		// キープボトル情報取得
		List<KeepBottle> bottleList = KeepBottleDAO.selectByCustomerId(loginCustomer.getId());
		request.setAttribute("bottleList", bottleList);

		// 顧客ホーム画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/customer_home.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * POSTはGETと同じ処理にする
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

