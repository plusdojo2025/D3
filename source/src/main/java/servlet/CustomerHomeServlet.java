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

import dao.EventDAO;
import dao.KeepBottleDAO2;
import dto.Customer;
import dto.Event;
import dto.KeepBottle;

@WebServlet("/CustomerHomeServlet")
public class CustomerHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CustomerHomeServlet() {
		super();
	}

	/**
	 * 顧客ホーム画面表示（GETリクエスト対応）
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// セッションからログイン中の顧客情報を取得
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer) session.getAttribute("customer");

		// 未ログインならログインページへリダイレクト
		if (loginCustomer == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}

		// ニックネーム表示用
		String nickname = loginCustomer.getCustomer_name();
		request.setAttribute("nickname", nickname);

		// お知らせ取得（最新順で3件）
		EventDAO eventDao = new EventDAO();
		List<Event> eventList = eventDao.select(); 
		request.setAttribute("eventList", eventList);

		// キープボトル取得（そのユーザーの）
		KeepBottleDAO2 bottleDao = new KeepBottleDAO2();
		List<KeepBottle> bottleList = bottleDao.selectByCustomerId(loginCustomer.getCustomer_id());
		request.setAttribute("bottleList", bottleList);


		// フォワード先を指定（WEB-INF配下）
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/customer_home.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * POSTはGETと同じ処理を行う
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
