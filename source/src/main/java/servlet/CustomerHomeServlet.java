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

		// ニックネームの取得（customer_nameで代用）
		String nickname = loginCustomer.getCustomer_name();
		request.setAttribute("nickname", nickname);

		// お知らせ取得（最新順）
		EventDAO eventDao = new EventDAO();
		List<Event> eventList = eventDao.select();
		request.setAttribute("eventList", eventList);

		// キープボトル情報取得
		KeepBottleDAO2 bottleDao = new KeepBottleDAO2();
		List<KeepBottle> bottleList = bottleDao.selectByCustomerId(loginCustomer.getCustomer_id());
		request.setAttribute("bottleList", bottleList);

		// 顧客ホーム画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/customer_home.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
