package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StoreMemoDAO;
import dao.VisitorDAO;
import dto.Store;
import dto.StoreMemo;
import dto.Visitor;

@WebServlet("/StoreBusinessServlet")
public class StoreBusinessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// セッションからログイン中の店舗情報を取得
		HttpSession session = request.getSession();
		Store loginStore = (Store) session.getAttribute("store");

		// ログインしていない場合はログイン画面へリダイレクト
		if (loginStore == null) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}

		int storeId = loginStore.getStore_id();
		if (storeId < 1) {
			response.sendRedirect(request.getContextPath() + "/LoginServlet");
			return;
		}

		// 来店者表示
		VisitorDAO dao = new VisitorDAO();
		List<Visitor> visitor = dao.getCurrentVisitorByStoreId(storeId);

		request.setAttribute("visitors", visitor);

		// 連絡事項
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(timestamp);

		StoreMemoDAO storeMemoDAO = new StoreMemoDAO();
		List<StoreMemo> memoList = storeMemoDAO.getStoreMemoByDate(storeId, date);

		request.setAttribute("memoList", memoList);

		// 業務画面ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StoreBusiness.jsp");

		dispatcher.forward(request, response);
	}
}
